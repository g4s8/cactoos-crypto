/**
 * MIT License
 *
 * Copyright (c) 2018 Kirill
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files
 * (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights * to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package org.cactoos.crypto.digest;

import java.io.InputStream;
import java.security.MessageDigest;
import org.cactoos.Bytes;
import org.cactoos.Input;
import org.cactoos.Scalar;
import org.cactoos.crypto.common.ChunkUpdate;
import org.cactoos.scalar.IoChecked;

/**
 * Digest of input.
 * @author Kirill (g4s8.public@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class DigestOf implements Bytes {
    /**
     * Source.
     */
    private final Input src;
    /**
     * Digest.
     */
    private final IoChecked<MessageDigest> dgst;

    /**
     * Ctor.
     * @param input Input source
     * @param digest Message digest
     */
    public DigestOf(final Input input, final Scalar<MessageDigest> digest) {
        this.src = input;
        this.dgst = new IoChecked<>(digest);
    }

    @Override
    public byte[] asBytes() throws Exception {
        final MessageDigest digest = this.dgst.value();
        try (final InputStream stream = this.src.stream()) {
            new ChunkUpdate((bts, len) -> digest.update(bts, 0, len))
                .exec(stream);
        }
        return digest.digest();
    }
}
