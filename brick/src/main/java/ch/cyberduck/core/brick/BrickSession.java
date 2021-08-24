package ch.cyberduck.core.brick;

/*
 * Copyright (c) 2002-2019 iterate GmbH. All rights reserved.
 * https://cyberduck.io/
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

import ch.cyberduck.core.ConnectionCallback;
import ch.cyberduck.core.Credentials;
import ch.cyberduck.core.DefaultIOExceptionMappingService;
import ch.cyberduck.core.DisabledConnectionCallback;
import ch.cyberduck.core.Host;
import ch.cyberduck.core.HostKeyCallback;
import ch.cyberduck.core.HostUrlProvider;
import ch.cyberduck.core.ListService;
import ch.cyberduck.core.LocaleFactory;
import ch.cyberduck.core.LoginCallback;
import ch.cyberduck.core.PreferencesUseragentProvider;
import ch.cyberduck.core.URIEncoder;
import ch.cyberduck.core.UrlProvider;
import ch.cyberduck.core.exception.BackgroundException;
import ch.cyberduck.core.exception.ConnectionCanceledException;
import ch.cyberduck.core.exception.LoginCanceledException;
import ch.cyberduck.core.features.AttributesFinder;
import ch.cyberduck.core.features.Copy;
import ch.cyberduck.core.features.Delete;
import ch.cyberduck.core.features.Directory;
import ch.cyberduck.core.features.Find;
import ch.cyberduck.core.features.Lock;
import ch.cyberduck.core.features.Move;
import ch.cyberduck.core.features.MultipartWrite;
import ch.cyberduck.core.features.PromptUrlProvider;
import ch.cyberduck.core.features.Read;
import ch.cyberduck.core.features.Timestamp;
import ch.cyberduck.core.features.Touch;
import ch.cyberduck.core.features.Upload;
import ch.cyberduck.core.features.Write;
import ch.cyberduck.core.http.HttpSession;
import ch.cyberduck.core.local.BrowserLauncherFactory;
import ch.cyberduck.core.preferences.HostPreferences;
import ch.cyberduck.core.proxy.Proxy;
import ch.cyberduck.core.ssl.X509KeyManager;
import ch.cyberduck.core.ssl.X509TrustManager;
import ch.cyberduck.core.threading.CancelCallback;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.Uninterruptibles;

public class BrickSession extends HttpSession<CloseableHttpClient> {
    private static final Logger log = Logger.getLogger(BrickSession.class);

    private String apiKey;

    public BrickSession(final Host host, final X509TrustManager trust, final X509KeyManager key) {
        super(host, trust, key);
    }

    public String getApiKey() {
        return apiKey;
    }

    @Override
    protected CloseableHttpClient connect(final Proxy proxy, final HostKeyCallback key, final LoginCallback prompt, final CancelCallback cancel) {
        final HttpClientBuilder configuration = builder.build(proxy, this, prompt);
        configuration.setServiceUnavailableRetryStrategy(new BrickUnauthorizedRetryStrategy(this, prompt, cancel));
        configuration.addInterceptorLast(new BrickPreferencesRequestInterceptor());
        return configuration.build();
    }

    @Override
    public void login(final Proxy proxy, final LoginCallback prompt, final CancelCallback cancel) throws BackgroundException {
        final Credentials credentials;
        if(host.getCredentials().isPasswordAuthentication()) {
            credentials = host.getCredentials();
        }
        else {
            // No prompt on explicit connect
            credentials = this.pair(host, prompt, cancel);
            credentials.setSaved(true);
        }
        apiKey = credentials.getPassword();
    }

    @Override
    protected void logout() throws BackgroundException {
        try {
            client.close();
        }
        catch(IOException e) {
            throw new DefaultIOExceptionMappingService().map(e);
        }
    }

    public Credentials pair(final Host bookmark, final ConnectionCallback prompt, final CancelCallback cancel) throws BackgroundException {
        final String token = new BrickCredentialsConfigurator().configure(host).getToken();
        if(log.isDebugEnabled()) {
            log.debug(String.format("Attempt pairing with token %s", token));
        }
        final BrickPairingSchedulerFeature scheduler = new BrickPairingSchedulerFeature(this, token, bookmark, cancel);
        // Operate in background until canceled
        final ConnectionCallback lock = new DisabledConnectionCallback() {
            final CountDownLatch lock = new CountDownLatch(1);

            @Override
            public void close(final String input) {
                prompt.close(input);
                // Continue with login
                lock.countDown();
            }

            @Override
            public void warn(final Host bookmark, final String title, final String message,
                             final String defaultButton, final String cancelButton, final String preference) throws ConnectionCanceledException {
                prompt.warn(bookmark, title, message, defaultButton, cancelButton, preference);
                try {
                    if(!BrowserLauncherFactory.get().open(
                        String.format("%s/login_from_desktop?pairing_key=%s&platform=%s&computer=%s",
                            new HostUrlProvider().withUsername(false).withPath(false).get(host),
                            token,
                            URIEncoder.encode(new PreferencesUseragentProvider().get()), URIEncoder.encode(InetAddress.getLocalHost().getHostName()))
                    )) {
                        throw new LoginCanceledException();
                    }
                }
                catch(UnknownHostException e) {
                    throw new ConnectionCanceledException(e);
                }
                final long timeout = new HostPreferences(host).getLong("brick.pairing.interrupt.ms");
                final long start = System.currentTimeMillis();
                // Wait for status response from pairing scheduler
                while(!Uninterruptibles.awaitUninterruptibly(lock, new HostPreferences(host).getLong("brick.pairing.interval.ms"), TimeUnit.MILLISECONDS)) {
                    cancel.verify();
                    if(System.currentTimeMillis() - start > timeout) {
                        throw new ConnectionCanceledException(String.format("Interrupt wait for pairing key after %d", timeout));
                    }
                }
            }
        };
        // Poll for pairing key until canceled
        scheduler.repeat(lock);
        // Await reply
        lock.warn(bookmark, String.format("%s %s", LocaleFactory.localizedString("Login", "Login"), bookmark.getHostname()),
            LocaleFactory.localizedString("The desktop application session has expired or been revoked.", "Brick"),
            LocaleFactory.localizedString("Open in Web Browser"), LocaleFactory.localizedString("Cancel"), null);
        // Not canceled
        scheduler.shutdown();
        // When connect attempt is interrupted will throw connection cancel failure
        cancel.verify();
        return bookmark.getCredentials();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T _getFeature(final Class<T> type) {
        if(type == Upload.class) {
            return (T) new BrickThresholdUploadFeature(this);
        }
        if(type == MultipartWrite.class) {
            return (T) new BrickMultipartWriteFeature(this);
        }
        if(type == Write.class) {
            return (T) new BrickWriteFeature(this);
        }
        if(type == Touch.class) {
            return (T) new BrickTouchFeature(this);
        }
        if(type == Timestamp.class) {
            return (T) new BrickTimestampFeature(this);
        }
        if(type == UrlProvider.class) {
            return (T) new BrickUrlProvider(host);
        }
        if(type == ListService.class) {
            return (T) new BrickListService(this);
        }
        if(type == Read.class) {
            return (T) new BrickReadFeature(this);
        }
        if(type == Move.class) {
            return (T) new BrickMoveFeature(this);
        }
        if(type == Copy.class) {
            return (T) new BrickCopyFeature(this);
        }
        if(type == Directory.class) {
            return (T) new BrickDirectoryFeature(this);
        }
        if(type == Delete.class) {
            return (T) new BrickDeleteFeature(this);
        }
        if(type == Find.class) {
            return (T) new BrickFindFeature(this);
        }
        if(type == AttributesFinder.class) {
            return (T) new BrickAttributesFinderFeature(this);
        }
        if(type == Lock.class) {
            return (T) new BrickLockFeature(this);
        }
        if(type == PromptUrlProvider.class) {
            return (T) new BrickShareFeature(this);
        }
        return super._getFeature(type);
    }

}
