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
package org.cactoos.crypto.mac;

import java.security.Key;
import javax.crypto.Mac;
import org.cactoos.Proc;

/**
 * MAC specification.
 * @author Kirill (g4s8.public@gmail.com)
 * @version $Id$
 * @since 0.1
 * @todo #1:30min Add all missed MAC specs, e.g. with AlgorithmParameterSpec
 *  etc.
 */
public final class MacSpec implements Proc<Mac> {
    /**
     * Init procedure.
     */
    private final Proc<Mac> init;

    /**
     * Ctor.
     * @param key MAC key
     */
    public MacSpec(final Key key) {
        this(mac -> mac.init(key));
    }

    /**
     * Primary ctor.
     * @param init Init procedure
     */
    private MacSpec(final Proc<Mac> init) {
        this.init = init;
    }

    @Override
    public void exec(final Mac input) throws Exception {
        this.init.exec(input);
    }
}
