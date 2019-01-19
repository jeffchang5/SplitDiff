package io.jeffchang.splitdiff.ui.gitdiff.interactor

import io.jeffchang.splitdiff.ui.gitdiff.repository.PullRequestRepository
import javax.inject.Inject

class PullRequestInteractor @Inject constructor(
        private val pullRequestRepository: PullRequestRepository) {

    fun getPullRequests(username: String, repository: String) =
            pullRequestRepository.getPullRequests(username, repository)


}