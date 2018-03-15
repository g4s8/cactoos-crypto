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

import java.io.IOException;
import java.security.MessageDigest;
import org.cactoos.Scalar;
import org.cactoos.scalar.IoCheckedScalar;

/**
 * Make a digest for algorithm.
 * @author Kirill (g4s8.public@gmail.com)
 * @version $Id$
 * @since 0.1
 * @todo #1:30min Add all missed constructors, it should allow to create
 *  a Digest with Security provider instance or name specified.
 */
public final class DigestFrom implements Scalar<MessageDigest> {
    /**
     * Source digest.
     */
    private final IoCheckedScalar<MessageDigest> src;
    /**
     * Ctor.
     * @param alg Algorithm name
     */
    public DigestFrom(final String alg) {
        this(() -> MessageDigest.getInstance(alg));
    }
    /**
     * Ctor.
     * @param src Source
     */
    private DigestFrom(final Scalar<MessageDigest> src) {
        this.src = new IoCheckedScalar<>(src);
    }

    @Override
    public MessageDigest value() throws IOException {
        return this.src.value();
    }
}
