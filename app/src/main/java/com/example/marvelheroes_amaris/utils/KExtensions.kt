package com.example.marvelheroes_amaris.utils

import java.math.BigInteger
import java.security.MessageDigest

/**
 * Returns the MD5 ecryption of ta given string
 */
fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}