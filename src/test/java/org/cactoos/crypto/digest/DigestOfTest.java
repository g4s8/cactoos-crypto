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
import org.cactoos.io.InputOf;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Test case for {@link DigestOf}.
 * @author Kirill (g4s8.public@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle LineLengthCheck (500 lines)
 * @checkstyle JavadocMethodCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class DigestOfTest {
    @Test
    public void calculateDigest() throws IOException {
        MatcherAssert.assertThat(
            new DigestOf(
                new InputOf(
                    new byte[]{
                        (byte) 0x11, (byte) 0x22, (byte) 0x33, (byte) 0x44,
                        (byte) 0x55, (byte) 0x66, (byte) 0x77, (byte) 0x88,
                        (byte) 0x99, (byte) 0xAA, (byte) 0xBB, (byte) 0xCC,
                        (byte) 0xDD, (byte) 0xEE, (byte) 0xFF, (byte) 0x00,
                    }
                ),
                new DigestFrom("SHA-256")
            ).asBytes(),
            CoreMatchers.equalTo(
                new byte[]{
                    (byte) -112, (byte) -120, (byte) -125, (byte) 84,
                    (byte) 90, (byte) -44, (byte) 24, (byte) 103,
                    (byte) 40, (byte) -5, (byte) -2, (byte) 0,
                    (byte) -18, (byte) 61, (byte) 104, (byte) -93,
                    (byte) 0, (byte) -21, (byte) -2, (byte) -88,
                    (byte) -76, (byte) -120, (byte) -118, (byte) 113,
                    (byte) 109, (byte) 33, (byte) 49, (byte) 119,
                    (byte) 42, (byte) 22, (byte) 89, (byte) 2,
                }
            )
        );
    }
}
