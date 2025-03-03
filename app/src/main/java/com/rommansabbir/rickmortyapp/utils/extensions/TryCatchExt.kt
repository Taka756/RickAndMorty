package com.rommansabbir.rickmortyapp.utils.extensions

inline fun <T> executeBodyOrReturnNull(crossinline body: () -> T): T? {
    return try {
        body.invoke()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun nullString() = "---"




