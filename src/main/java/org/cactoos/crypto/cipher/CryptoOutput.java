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

import java.io.OutputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import org.cactoos.Func;
import org.cactoos.Output;
import org.cactoos.func.IoCheckedFunc;

/**
 * Encrypted or decrypted output.
 * @author Kirill (g4s8.public@gmail.com)
 * @version $Id$
 * @see CryptoOutput.Enc
 * @see CryptoOutput.Dec
 * @since 0.1
 */
public final class CryptoOutput implements Output {
    /**
     * Output to encrypt/decrypt.
     */
    private final Output src;
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
    public CryptoOutput(
        final Output origin,
        final Func<Integer, Cipher> cipher,
        final int mode
    ) {
        this.src = origin;
        this.cphr = new IoCheckedFunc<>(cipher);
        this.mode = mode;
    }

    @Override
    public OutputStream stream() throws Exception {
        return new CipherOutputStream(
            this.src.stream(),
            this.cphr.apply(this.mode)
        );
    }

    /**
     * Encrypted output stream.
     */
    public static final class Enc implements Output {
        /**
         * Origin.
         */
        private final Output output;

        /**
         * Ctor.
         * @param origin Origin output
         * @param cipher Cipher source
         */
        public Enc(
            final Output origin,
            final Func<Integer, Cipher> cipher
        ) {
            this.output = new CryptoOutput(
                origin,
                cipher,
                Cipher.ENCRYPT_MODE
            );
        }

        @Override
        public OutputStream stream() throws Exception {
            return this.output.stream();
        }
    }

    /**
     * Decrypted output stream.
     */
    public static final class Dec implements Output {
        /**
         * Origin.
         */
        private final Output output;

        /**
         * Ctor.
         * @param origin Origin output
         * @param cipher Cipher source
         */
        public Dec(
            final Output origin,
            final Func<Integer, Cipher> cipher
        ) {
            this.output = new CryptoOutput(
                origin,
                cipher,
                Cipher.DECRYPT_MODE
            );
        }

        @Override
        public OutputStream stream() throws Exception {
            return this.output.stream();
        }
    }
}
