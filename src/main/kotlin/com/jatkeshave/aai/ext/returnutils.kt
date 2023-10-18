package com.jatkeshave.aai.ext

inline infix fun <R>R?.orElse(block: () -> R): R {
    return this ?: block()
}

infix fun <R>R?.orElse(defaultValue: R): R {
    return this ?: defaultValue
}
