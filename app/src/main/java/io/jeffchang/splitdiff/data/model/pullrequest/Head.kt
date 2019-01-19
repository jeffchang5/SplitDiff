package io.jeffchang.splitdiff.data.model.pullrequest

data class Head(
	val ref: String? = null,
	val repo: Repo? = null,
	val label: String? = null,
	val sha: String? = null,
	val user: User? = null
)
