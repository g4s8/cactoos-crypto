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

import java.io.IOException;
import java.io.InputStream;
import javax.crypto.Mac;
import org.cactoos.Bytes;
import org.cactoos.Input;
import org.cactoos.Scalar;
import org.cactoos.crypto.common.ChunkUpdate;
import org.cactoos.scalar.IoCheckedScalar;

/**
 * MAC of input.
 * @author Kirill (g4s8.public@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class MacOf implements Bytes {
    /**
     * Input source.
     */
    private final Input src;
    /**
     * MAC source.
     */
    private final IoCheckedScalar<Mac> mcc;
    /**
     * Ctor.
     * @param input Input
     * @param mac MAC source
     */
    public MacOf(final Input input, final Scalar<Mac> mac) {
        this.src = input;
        this.mcc = new IoCheckedScalar<>(mac);
    }

    @Override
    public byte[] asBytes() throws IOException {
        final Mac mac = this.mcc.value();
        try (final InputStream stream = this.src.stream()) {
            new ChunkUpdate(
                (bts, length) -> mac.update(bts, 0, length)
            ).exec(stream);
        }
        return mac.doFinal();
    }
}
