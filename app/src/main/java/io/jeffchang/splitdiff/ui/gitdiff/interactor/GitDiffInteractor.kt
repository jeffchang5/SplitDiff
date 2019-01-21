package io.jeffchang.splitdiff.ui.gitdiff.interactor

import io.jeffchang.splitdiff.ui.gitdiff.repository.GitDiffRepository
import io.jeffchang.splitdiff.ui.pullrequests.repository.PullRequestRepository
import javax.inject.Inject

class GitDiffInteractor @Inject constructor(
        private val gitDiffRepository: GitDiffRepository) {

    fun getGitDiff(diffUrl: String) =
            gitDiffRepository.getDifDiff(diffUrl)

}