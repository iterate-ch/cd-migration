package ch.cyberduck.ui.cocoa.controller;

/*
 * Copyright (c) 2002-2016 iterate GmbH. All rights reserved.
 * https://cyberduck.io/
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

import ch.cyberduck.binding.Action;
import ch.cyberduck.binding.Outlet;
import ch.cyberduck.binding.application.NSButton;
import ch.cyberduck.binding.application.NSCell;
import ch.cyberduck.binding.application.NSControl;
import ch.cyberduck.binding.application.NSSecureTextField;
import ch.cyberduck.binding.application.NSTextField;
import ch.cyberduck.binding.application.SheetCallback;
import ch.cyberduck.binding.foundation.NSAttributedString;
import ch.cyberduck.binding.foundation.NSNotification;
import ch.cyberduck.core.Credentials;
import ch.cyberduck.core.Host;
import ch.cyberduck.core.HostPasswordStore;
import ch.cyberduck.core.LoginOptions;
import ch.cyberduck.core.PasswordStoreFactory;
import ch.cyberduck.core.preferences.Preferences;
import ch.cyberduck.core.preferences.PreferencesFactory;
import ch.cyberduck.ui.LoginInputValidator;

import org.apache.commons.lang3.StringUtils;
import org.rococoa.Foundation;

public class ConnectionController extends BookmarkController {

    private final HostPasswordStore keychain
            = PasswordStoreFactory.get();

    private final Preferences preferences
            = PreferencesFactory.get();

    @Outlet
    private NSTextField passwordField;
    @Outlet
    private NSTextField passwordLabel;
    @Outlet
    private NSButton keychainCheckbox;

    public ConnectionController(final Host bookmark) {
        this(bookmark, bookmark.getCredentials());
    }

    public ConnectionController(final Host bookmark, final Credentials credentials) {
        this(bookmark, credentials, new LoginOptions(bookmark.getProtocol()));
    }

    public ConnectionController(final Host bookmark, final Credentials credentials, final LoginOptions options) {
        super(bookmark, credentials, new LoginInputValidator(credentials, bookmark.getProtocol(), options), options);
    }

    @Override
    public void awakeFromNib() {
        super.awakeFromNib();
        if(options.user) {
            window.makeFirstResponder(usernameField);
        }
        if(options.password) {
            window.makeFirstResponder(passwordField);
        }
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    protected String getBundleName() {
        return "Connection";
    }

    @Override
    public void callback(final int returncode) {
        if(SheetCallback.CANCEL_OPTION == returncode) {
            credentials.setPassword(null);
        }
    }

    @Override
    public void windowDidBecomeKey(final NSNotification notification) {
        // Reset credentials
        this.updateField(usernameField, credentials.getUsername());
        this.updateField(passwordField, credentials.getPassword());
    }

    public void setPasswordField(NSSecureTextField field) {
        this.passwordField = field;
        this.updateField(this.passwordField, credentials.getPassword());
        this.notificationCenter.addObserver(this.id(),
                Foundation.selector("passwordFieldTextDidChange:"),
                NSControl.NSControlTextDidChangeNotification,
                this.passwordField);
        this.addObserver(new BookmarkObserver() {
            @Override
            public void change(final Host bookmark) {
                passwordField.cell().setPlaceholderString(options.getPasswordPlaceholder());
                passwordField.setEnabled(options.password && !credentials.isAnonymousLogin());
                if(options.keychain) {
                    if(StringUtils.isBlank(bookmark.getHostname())) {
                        return;
                    }
                    if(StringUtils.isBlank(credentials.getUsername())) {
                        return;
                    }
                    final String password = keychain.getPassword(bookmark.getProtocol().getScheme(),
                            bookmark.getPort(),
                            bookmark.getHostname(),
                            credentials.getUsername());
                    if(StringUtils.isNotBlank(password)) {
                        credentials.setPassword(password);
                        updateField(passwordField, password);
                    }
                }
            }
        });
    }

    @Action
    public void passwordFieldTextDidChange(NSNotification notification) {
        credentials.setPassword(passwordField.stringValue());
    }

    public void setPasswordLabel(NSTextField passwordLabel) {
        this.passwordLabel = passwordLabel;
        this.addObserver(new BookmarkObserver() {
            @Override
            public void change(final Host bookmark) {
                passwordLabel.setAttributedStringValue(NSAttributedString.attributedStringWithAttributes(
                        StringUtils.isNotBlank(options.getPasswordPlaceholder()) ? String.format("%s:",
                                options.getPasswordPlaceholder()) : StringUtils.EMPTY, LABEL_ATTRIBUTES
                ));
            }
        });
    }

    public void setKeychainCheckbox(NSButton keychainCheckbox) {
        this.keychainCheckbox = keychainCheckbox;
        this.keychainCheckbox.setState(options.save ? NSCell.NSOnState : NSCell.NSOffState);
        this.keychainCheckbox.setTarget(this.id());
        this.keychainCheckbox.setAction(Foundation.selector("keychainCheckboxClicked:"));
        this.addObserver(new BookmarkObserver() {
            @Override
            public void change(final Host bookmark) {
                keychainCheckbox.setEnabled(options.keychain);
                keychainCheckbox.setState(credentials.isSaved() ? NSCell.NSOnState : NSCell.NSOffState);
            }
        });
    }

    @Action
    public void keychainCheckboxClicked(final NSButton sender) {
        credentials.setSaved(sender.state() == NSCell.NSOnState);
    }
}
