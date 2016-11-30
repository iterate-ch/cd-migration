package ch.cyberduck.core.cryptomator;

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

import ch.cyberduck.core.DefaultIOExceptionMappingService;
import ch.cyberduck.core.Path;
import ch.cyberduck.core.Session;
import ch.cyberduck.core.cryptomator.impl.CryptoVault;
import ch.cyberduck.core.exception.BackgroundException;
import ch.cyberduck.core.features.Read;
import ch.cyberduck.core.transfer.TransferStatus;

import org.cryptomator.cryptolib.api.Cryptor;
import org.cryptomator.cryptolib.api.FileHeader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class CryptoReadFeature implements Read {

    private final Session<?> session;
    private final Read delegate;
    private final CryptoVault cryptomator;

    public CryptoReadFeature(final Session<?> session, final Read delegate, final CryptoVault cryptomator) {
        this.session = session;
        this.delegate = delegate;
        this.cryptomator = cryptomator;
    }

    @Override
    public InputStream read(final Path file, final TransferStatus status) throws BackgroundException {
        try {
            final Path encrypted = cryptomator.encrypt(session, file);
            // Header
            final Cryptor cryptor = cryptomator.getCryptor();
            final ByteBuffer existingHeaderBuf = ByteBuffer.allocate(cryptor.fileHeaderCryptor().headerSize());
            final InputStream cryptoStream = delegate.read(encrypted, status);
            final int read = cryptoStream.read(existingHeaderBuf.array());
            final FileHeader header = cryptor.fileHeaderCryptor().decryptHeader(existingHeaderBuf);
            return new CryptoInputStream(cryptoStream, cryptor, header);
        }
        catch(IOException e) {
            throw new DefaultIOExceptionMappingService().map(e);
        }
    }

    @Override
    public boolean offset(final Path file) throws BackgroundException {
        return delegate.offset(file);
    }
}
