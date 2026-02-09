package com.rabbah.domain.ext

import io.ktor.util.decodeBase64String

fun String.decodeBase64(): String {
    // A JWT uses Base64URL encoding, so we need to replace the URL-safe
    // characters with their standard Base64 equivalents.
    val standardBase64 = this.replace('-', '+').replace('_', '/')
    // Ktor's decodeBase64String handles padding and decodes directly to a UTF-8 String.
    return standardBase64.decodeBase64String()
}
