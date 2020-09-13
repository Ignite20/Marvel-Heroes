package com.example.marvelheroes_amaris.common

import com.example.marvelheroes_amaris.utils.md5

fun calculateMD5(vararg args: String): String {
    var builder = StringBuilder()
    args.forEach {
        builder.append(it)
    }
    return builder.toString().md5()
}