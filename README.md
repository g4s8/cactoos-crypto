# CryptoCactoos

[![EO principles respected here](http://www.elegantobjects.org/badge.svg)](http://www.elegantobjects.org)
[![Managed by Zerocracy](https://www.0crat.com/badge/C9QEPABRP.svg)](https://www.0crat.com/p/C9QEPABRP)
[![DevOps By Rultor.com](http://www.rultor.com/b/g4s8/CryptoCactoos)](http://www.rultor.com/p/g4s8/CryptoCactoos)

[![Bintray](https://api.bintray.com/packages/g4s8/mvn/com.g4s8.cryptoactoos/images/download.svg)](https://bintray.com/g4s8/mvn/com.g4s8.cryptocactoos/_latestVersion)
[![Build Status](https://img.shields.io/travis/g4s8/CryptoCactoos.svg?style=flat-square)](https://travis-ci.org/g4s8/CryptoCactoos)
[![PDD status](http://www.0pdd.com/svg?name=g4s8/CryptoCactoos)](http://www.0pdd.com/p?name=g4s8/CryptoCactoos)
[![License](https://img.shields.io/github/license/g4s8/CryptoCactoos.svg?style=flat-square)](https://github.com/g4s8/CryptoCactoos/blob/master/LICENSE)
[![Test Coverage](https://img.shields.io/codecov/c/github/g4s8/CryptoCactoos.svg?style=flat-square)](https://codecov.io/github/g4s8/CryptoCactoos?branch=master)

Cryptography extensions for [Cactoos library](https://github.com/yegor256/cactoos).

### Cipher as input/output
To encrypt/decrypt an input:
```java
final Scalar<Cipher> cipher = new CipherFrom(
    "AES/CBC/PKCS5Padding",
    new CipherSpec(key, iv)
);
final Input encrypted = new CryptoInput.Enc(
  new InputOf(file),
  cipher
);
final Input decrypted = new CryptoInput.Dec(
  encrypted,
  cipher
);
```

To encrypt/decrypt an output:
```java
final Scalar<Cipher> cipher = new CipherFrom(
    "AES/CBC/PKCS5Padding",
    new CipherSpec(key, iv)
);
final Output decrypting = new CryptoOutput.Dec(
  new OutputTo(file),
  cipher
);
final Output encrypting = new CryptoOutput.Enc(
  decrypting,
  cipher
);
```

### MAC (Message Authentication Code) as Bytes:
To compute MAC of input:
```java
final Bytes hmac = new MacOf(
  new InputOf(file),
  new MacFrom(
    "HmacSHA256",
    new MacSpec(key)
  )
);
```

### MessageDigest as Bytes:
To compute message-digest of input:
```java
final Bytes digest = new DigestOf(
  new InputOf(file),
  new DigestFrom("SHA-256")
);
```

## Install
mvn:
```xml
<dependency>
  <groupId>com.g4s8</groupId>
  <artifactId>cryptocactoos</artifactId>
</dependency>
```

gradle:
```gradle
  "com.g4s8:cryptocactoos:<vesion>"
```
