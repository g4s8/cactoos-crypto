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
package org.cactoos.crypto.common;

import java.io.IOException;
import java.io.InputStream;
import org.cactoos.BiProc;
import org.cactoos.Proc;
import org.cactoos.func.IoCheckedBiProc;

/**
 * Update procedure by chunks.
 * @author Kirill (g4s8.public@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ChunkUpdate implements Proc<InputStream> {
    /**
     * Default buffer size.
     */
    private static final int DEFAULT_BUFFER = 2 << 1;
    /**
     * Update procedure.
     */
    private final IoCheckedBiProc<byte[], Integer> upd;
    /**
     * Buffer size.
     */
    private final int size;
    /**
     * Ctor.
     * @param update Update procedure (byte-array, bytes length)
     */
    public ChunkUpdate(final BiProc<byte[], Integer> update) {
        this(update, ChunkUpdate.DEFAULT_BUFFER);
    }
    /**
     * Ctor.
     * @param update Update procedure (byte-array, bytes length)
     * @param size Buffer size
     */
    public ChunkUpdate(final BiProc<byte[], Integer> update, final int size) {
        this.upd = new IoCheckedBiProc<>(update);
        this.size = size;
    }

    @Override
    public void exec(final InputStream input) throws IOException {
        final byte[] buf = new byte[this.size];
        for (int read = input.read(buf); read >= 0; read = input.read(buf)) {
            this.upd.exec(buf, read);
        }
    }
}
