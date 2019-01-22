package io.jeffchang.splitdiff.data.model.gitdiff

data class Content(
        val content: String,
        val type: Type
) {
    enum class Type {
        CHANGE, SAME
    }
}