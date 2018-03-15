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

import javax.crypto.spec.SecretKeySpec;
import org.cactoos.io.InputOf;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Test case for {@link MacOf}.
 * @author Kirill (g4s8.public@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle LineLengthCheck (500 lines)
 * @checkstyle JavadocMethodCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class MacOfTest {
    @Test
    public void calculateMac() throws Exception {
        MatcherAssert.assertThat(
            new MacOf(
                new InputOf(
                    new byte[]{
                        (byte) 0x00, (byte) 0x11, (byte) 0x22, (byte) 0x33,
                    }
                ),
                new MacFrom(
                    "HmacSHA256",
                    new MacSpec(
                        new SecretKeySpec(
                            new byte[]{
                                (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03,
                                (byte) 0xF0, (byte) 0xF1, (byte) 0xF2, (byte) 0xF3,
                                (byte) 0xC0, (byte) 0xC1, (byte) 0xC2, (byte) 0xC3,
                                (byte) 0x90, (byte) 0x91, (byte) 0x92, (byte) 0x93,
                            },
                            "HmacSHA256"
                        )
                    )
                )
            ).asBytes(),
            CoreMatchers.equalTo(
                new byte[]{
                    (byte) -48, (byte) 95, (byte) 87, (byte) 27,
                    (byte) 123, (byte) 116, (byte) -65, (byte) 107,
                    (byte) 41, (byte) 51, (byte) -46, (byte) -42,
                    (byte) -125, (byte) 18, (byte) 101, (byte) -81,
                    (byte) -115, (byte) 36, (byte) 80, (byte) 19,
                    (byte) -114, (byte) -85, (byte) -62, (byte) 92,
                    (byte) -89, (byte) 81, (byte) 111, (byte) -90,
                    (byte) -55, (byte) 99, (byte) 40, (byte) 112,
                }
            )
        );
    }
}
