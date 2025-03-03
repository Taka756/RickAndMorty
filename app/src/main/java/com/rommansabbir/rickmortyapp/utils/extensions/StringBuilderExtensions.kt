package com.rommansabbir.rickmortyapp.utils.extensions

val stringBuilderLazyInstance by lazy { StringBuilder() }

inline fun Any.writeString(crossinline body: (builder: StringBuilder) -> Unit): String {
    return stringBuilderLazyInstance.apply {
        clear()
        body.invoke(this)
    }.toString()
}
