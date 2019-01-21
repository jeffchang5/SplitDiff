package io.jeffchang.splitdiff.data.model.gitdiff

import io.jeffchang.githubdiffparser.models.Range

data class Hunk(
        val fromList: List<String>,
        val toList: List<String>,
        val range: Range
)