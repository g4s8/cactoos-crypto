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

import java.io.IOException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import org.cactoos.BiProc;
import org.cactoos.func.IoCheckedBiProc;

/**
 * Cipher specification: key, alg-spec, random.
 * @author Kirill (g4s8.public@gmail.com)
 * @version $Id$
 * @since 0.1
 * @todo #1:30min Add all constructors to reference all
 *  Cipher.init methods (with random, specs, etc).
 */
public final class CipherSpec implements BiProc<Integer, Cipher> {
    /**
     * Init with mode procedure.
     */
    private final IoCheckedBiProc<Integer, Cipher> init;
    /**
     * Ctor.
     * @param key Cipher key
     * @param spec Algorithm spec
     */
    public CipherSpec(
        final Key key,
        final AlgorithmParameterSpec spec
    ) {
        this((mode, cipher) -> cipher.init(mode, key, spec));
    }
    /**
     * Primary ctor.
     * @param init Init procedure
     */
    private CipherSpec(final BiProc<Integer, Cipher> init) {
        this.init = new IoCheckedBiProc<>(init);
    }

    @Override
    public void exec(final Integer mode, final Cipher input)
        throws IOException {
        this.init.exec(mode, input);
    }
}
