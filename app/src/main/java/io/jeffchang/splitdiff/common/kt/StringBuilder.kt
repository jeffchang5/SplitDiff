package io.jeffchang.splitdiff.common.kt

import java.lang.StringBuilder

operator fun StringBuilder.plusAssign(stringList: List<String>) {
    stringList.forEach {
        if (!it.hasNewLine) {
            append(it + "\n")
        }
    }
}
