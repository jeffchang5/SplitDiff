package io.jeffchang.splitdiff.data.model.pullrequest

data class Base(
	val ref: String? = null,
	val repo: Repo? = null,
	val label: String? = null,
	val sha: String? = null,
	val user: User? = null
)
