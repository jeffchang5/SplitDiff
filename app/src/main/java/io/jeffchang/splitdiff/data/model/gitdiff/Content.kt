package io.jeffchang.splitdiff.data.model.gitdiff

data class Content(
        val text: String,
        val type: Type
) {
    enum class Type {
        CHANGE, SAME
    }
}