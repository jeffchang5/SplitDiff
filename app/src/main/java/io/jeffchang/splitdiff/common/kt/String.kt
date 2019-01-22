package io.jeffchang.splitdiff.common.kt

val String.appendNewLineIfNeeded: String
    get() = if (endsWith("\n")) {
        this
    } else {
        this + "\n"
    }