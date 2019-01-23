package io.jeffchang.splitdiff.data.model.gitdiff

// Represents every file that was changed and provides a list of hunks.
data class Diff(
        val fromName: String,
        val toName: String,
        val hunks: List<Hunk>
)
