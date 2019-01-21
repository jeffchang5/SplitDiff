package io.jeffchang.splitdiff.data.model.gitdiff

// Represents every file that was changed and provides a list of diffs.
data class FileDiff(
        val diffs: List<Diff>
)
