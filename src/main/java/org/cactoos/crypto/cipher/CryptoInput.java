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

import java.io.InputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import org.cactoos.Func;
import org.cactoos.Input;
import org.cactoos.func.IoCheckedFunc;

/**
 * Encrypted or decrypted input.
 * @author Kirill (g4s8.public@gmail.com)
 * @version $Id$
 * @see CryptoOutput.Dec
 * @see CryptoOutput.Enc
 * @since 0.1
 */
public final class CryptoInput implements Input {
    /**
     * Input to encrypt/decrypt.
     */
    private final Input src;
    /**
     * Cipher.
     */
    private final IoCheckedFunc<Integer, Cipher> cphr;
    /**
     * Cipher mode.
     */
    private final int mode;

    /**
     * Ctor.
     * @param origin Data source
     * @param cipher Cipher source
     * @param mode Cipher mode
     */
    public CryptoInput(
        final Input origin,
        final Func<Integer, Cipher> cipher,
        final int mode
    ) {
        this.src = origin;
        this.cphr = new IoCheckedFunc<>(cipher);
        this.mode = mode;
    }

    @Override
    public InputStream stream() throws Exception {
        return new CipherInputStream(
            this.src.stream(),
            this.cphr.apply(this.mode)
        );
    }

    /**
     * Encrypted input stream.
     */
    public static final class Enc implements Input {
        /**
         * Origin.
         */
        private final Input input;

        /**
         * Ctor.
         * @param origin Origin input
         * @param cipher Cipher source
         */
        public Enc(
            final Input origin,
            final Func<Integer, Cipher> cipher
        ) {
            this.input = new CryptoInput(origin, cipher, Cipher.ENCRYPT_MODE);
        }

        @Override
        public InputStream stream() throws Exception {
            return this.input.stream();
        }
    }

    /**
     * Decrypted input stream.
     */
    public static final class Dec implements Input {
        /**
         * Origin.
         */
        private final Input input;

        /**
         * Ctor.
         * @param origin Origin input
         * @param cipher Cipher source
         */
        public Dec(
            final Input origin,
            final Func<Integer, Cipher> cipher
        ) {
            this.input = new CryptoInput(origin, cipher, Cipher.DECRYPT_MODE);
        }

        @Override
        public InputStream stream() throws Exception {
            return this.input.stream();
        }
    }
}
