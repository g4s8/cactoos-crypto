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
package org.cactoos.crypto.cipher;

import javax.crypto.Cipher;
import org.cactoos.Func;
import org.cactoos.Scalar;
import org.cactoos.scalar.IoChecked;

/**
 * Cipher of algorithm with provided mode.
 * @author Kirill (g4s8.public@gmail.com)
 * @version $Id$
 * @since 0.1
 * @todo #1:30min Add all missed constructors, it should allow to create a
 *  Cipher with Security provider instance or name specified.
 */
public final class CipherFrom implements Func<Integer, Cipher> {
    /**
     * Cipher .
     */
    private final IoChecked<Cipher> cphr;
    /**
     * Cipher mode.
     */
    private final CipherSpec spec;

    /**
     * Ctor.
     * @param alg Algorithm
     * @param spec Cipher mode
     */
    public CipherFrom(final String alg, final CipherSpec spec) {
        this(() -> Cipher.getInstance(alg), spec);
    }

    /**
     * Primary ctor.
     * @param cipher Cipher
     * @param spec Mode
     */
    private CipherFrom(
        final Scalar<Cipher> cipher,
        final CipherSpec spec
    ) {
        this.cphr = new IoChecked<>(cipher);
        this.spec = spec;
    }

    @Override
    public Cipher apply(final Integer mode) throws Exception {
        final Cipher cipher = this.cphr.value();
        this.spec.exec(mode, cipher);
        return cipher;
    }
}
