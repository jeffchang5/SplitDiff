package io.jeffchang.splitdiff.data.model.gitdiff

import io.jeffchang.githubdiffparser.models.Range

data class Hunk(
        val fromList: List<Content>,
        val toList: List<Content>,
        val range: Range
)