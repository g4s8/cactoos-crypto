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

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.cactoos.io.BytesOf;
import org.cactoos.io.InputOf;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for {@link CryptoOutput}.
 * @author Kirill (g4s8.public@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle LineLengthCheck (500 lines)
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class CryptoInputTest {
    @Test
    public void encryptInput() throws Exception {
        MatcherAssert.assertThat(
            new BytesOf(
                new CryptoInput.Enc(
                    new InputOf(
                        new BytesOf(
                            (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03,
                            (byte) 0x04, (byte) 0x05, (byte) 0x06, (byte) 0x07,
                            (byte) 0x08, (byte) 0x09, (byte) 0x0A, (byte) 0x0B,
                            (byte) 0x0C, (byte) 0x0D, (byte) 0x0E, (byte) 0x0F
                        )
                    ),
                    new CipherFrom(
                        "AES/CBC/PKCS5Padding",
                        new CipherSpec(
                            new SecretKeySpec(
                                new byte[]{
                                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                                },
                                "AES"
                            ),
                            new IvParameterSpec(
                                new byte[]{
                                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                                }
                            )
                        )
                    )
                )
            ).asBytes(),
            CoreMatchers.equalTo(
                new byte[]{
                    (byte) 122, (byte) -54, (byte) 15, (byte) -39,
                    (byte) -68, (byte) -42, (byte) -20, (byte) 124,
                    (byte) -97, (byte) -105, (byte) 70, (byte) 102,
                    (byte) 22, (byte) -26, (byte) -94, (byte) -126,
                    (byte) 87, (byte) -122, (byte) 41, (byte) 78,
                    (byte) 100, (byte) 39, (byte) -125, (byte) 14,
                    (byte) 108, (byte) -93, (byte) 25, (byte) -59,
                    (byte) 10, (byte) -50, (byte) -93, (byte) -3,
                }
            )
        );
    }

    @Test
    public void decryptInput() throws Exception {
        MatcherAssert.assertThat(
            new BytesOf(
                new CryptoInput.Dec(
                    new InputOf(
                        new byte[]{
                            (byte) 79, (byte) -45, (byte) -15, (byte) 41,
                            (byte) -49, (byte) -127, (byte) -111, (byte) -123,
                            (byte) -42, (byte) 24, (byte) 113, (byte) -59,
                            (byte) 57, (byte) 15, (byte) -68, (byte) -106,
                            (byte) -5, (byte) -90, (byte) 25, (byte) -80,
                            (byte) 81, (byte) 117, (byte) 81, (byte) -86,
                            (byte) 56, (byte) 63, (byte) -15, (byte) -115,
                            (byte) -15, (byte) 12, (byte) 31, (byte) 8,
                        }
                    ),
                    new CipherFrom(
                        "AES/CBC/PKCS5Padding",
                        new CipherSpec(
                            new SecretKeySpec(
                                new byte[]{
                                    (byte) 15, (byte) -107, (byte) 113, (byte) -38,
                                    (byte) -66, (byte) 39, (byte) -16, (byte) 37,
                                    (byte) 57, (byte) -58, (byte) -10, (byte) 52,
                                    (byte) -19, (byte) -62, (byte) -86, (byte) 126,
                                },
                                "AES"
                            ),
                            new IvParameterSpec(
                                new byte[]{
                                    (byte) -28, (byte) 74, (byte) 52, (byte) -108,
                                    (byte) -71, (byte) 34, (byte) 68, (byte) 52,
                                    (byte) 112, (byte) 76, (byte) 69, (byte) -76,
                                    (byte) 101, (byte) 12, (byte) 16, (byte) -83,
                                }
                            )
                        )
                    )
                )
            ).asBytes(),
            Matchers.equalTo(
                new byte[]{
                    (byte) -54, (byte) 20, (byte) -109, (byte) 109,
                    (byte) -123, (byte) 125, (byte) -14, (byte) 123,
                    (byte) -17, (byte) 110, (byte) -57, (byte) 60,
                    (byte) -80, (byte) -122, (byte) 44, (byte) 75,
                }
            )
        );
    }
}
