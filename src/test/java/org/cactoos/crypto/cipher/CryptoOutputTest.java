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

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.cactoos.Output;
import org.cactoos.io.OutputTo;
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
public final class CryptoOutputTest {
    @Test
    public void encryptOutput() throws Exception {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        final Output out = new CryptoOutput.Enc(
            new OutputTo(baos),
            new CipherFrom(
                "AES/CBC/PKCS5Padding",
                new CipherSpec(
                    new SecretKeySpec(
                        new byte[] {
                            (byte) 27, (byte) -61, (byte) -81, (byte) 97,
                            (byte) 40, (byte) -55, (byte) -58, (byte) -86,
                            (byte) 89, (byte) 34, (byte) -7, (byte) 95,
                            (byte) 117, (byte) -103, (byte) 50, (byte) 91,
                        },
                        "AES"
                    ),
                    new IvParameterSpec(
                        new byte[] {
                            (byte) -53, (byte) 30, (byte) 6, (byte) -54,
                            (byte) 87, (byte) 109, (byte) 123, (byte) -17,
                            (byte) -1, (byte) -111, (byte) -66, (byte) 40,
                            (byte) -1, (byte) 34, (byte) 13, (byte) 84,
                        }
                    )
                )
            )
        );
        try (final OutputStream stream = out.stream()) {
            stream.write(
                new byte[] {
                    (byte) 32, (byte) -35, (byte) 31, (byte) -75,
                    (byte) 121, (byte) 111, (byte) 113, (byte) -85,
                    (byte) -27, (byte) 61, (byte) 8, (byte) 14,
                    (byte) -3, (byte) 122, (byte) 51, (byte) -43,
                }
            );
        }
        MatcherAssert.assertThat(
            baos.toByteArray(),
            Matchers.equalTo(
                new byte[] {
                    (byte) 6, (byte) 37, (byte) -117, (byte) 75,
                    (byte) -121, (byte) -23, (byte) -110, (byte) -83,
                    (byte) -31, (byte) 85, (byte) 56, (byte) -78,
                    (byte) 37, (byte) 49, (byte) 97, (byte) -112,
                    (byte) -86, (byte) -88, (byte) -107, (byte) -19,
                    (byte) 8, (byte) 106, (byte) -94, (byte) 87,
                    (byte) -108, (byte) 103, (byte) 43, (byte) 14,
                    (byte) -106, (byte) 95, (byte) -94, (byte) -125,
                }
            )
        );
    }

    @Test
    public void decryptOutput() throws Exception {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        final Output out = new CryptoOutput.Dec(
            new OutputTo(baos),
            new CipherFrom(
                "AES/CBC/PKCS5Padding",
                new CipherSpec(
                    new SecretKeySpec(
                        new byte[] {
                            (byte) -62, (byte) -113, (byte) -41, (byte) 9,
                            (byte) 25, (byte) 18, (byte) 5, (byte) -90,
                            (byte) -72, (byte) 28, (byte) -41, (byte) 109,
                            (byte) -92, (byte) 16, (byte) -108, (byte) -82,
                        },
                        "AES"
                    ),
                    new IvParameterSpec(
                        new byte[] {
                            (byte) 80, (byte) 41, (byte) -6, (byte) 91,
                            (byte) 17, (byte) -37, (byte) 116, (byte) -55,
                            (byte) -99, (byte) 12, (byte) 31, (byte) -125,
                            (byte) -91, (byte) -52, (byte) 93, (byte) 0,
                        }
                    )
                )
            )
        );
        try (final OutputStream stream = out.stream()) {
            stream.write(
                new byte[] {
                    (byte) 28, (byte) -31, (byte) -70, (byte) -8,
                    (byte) 72, (byte) 65, (byte) -63, (byte) -117,
                    (byte) -107, (byte) 15, (byte) -112, (byte) -90,
                    (byte) 73, (byte) 89, (byte) -81, (byte) 127,
                    (byte) -4, (byte) 39, (byte) 40, (byte) 69,
                    (byte) -42, (byte) -70, (byte) -92, (byte) -83,
                    (byte) 112, (byte) 12, (byte) -63, (byte) -103,
                    (byte) 92, (byte) -97, (byte) 17, (byte) 6,
                }
            );
        }
        MatcherAssert.assertThat(
            baos.toByteArray(),
            Matchers.equalTo(
                new byte[] {
                    (byte) -65, (byte) -108, (byte) 81, (byte) 52,
                    (byte) -42, (byte) 38, (byte) -1, (byte) 34,
                    (byte) -20, (byte) 77, (byte) 16, (byte) -45,
                    (byte) 119, (byte) 74, (byte) 38, (byte) 121,
                }
            )
        );
    }
}
