package io.jeffchang.splitdiff.common.kt

val String.hasNewLine: Boolean
    get() = endsWith("\n")