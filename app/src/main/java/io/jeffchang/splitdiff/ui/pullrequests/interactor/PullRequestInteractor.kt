package io.jeffchang.splitdiff.ui.pullrequests.interactor

import io.jeffchang.splitdiff.ui.pullrequests.repository.PullRequestRepository
import javax.inject.Inject

class PullRequestInteractor @Inject constructor(
        private val pullRequestRepository: PullRequestRepository) {

    fun getPullRequests(username: String, repository: String) =
            pullRequestRepository.getPullRequests(username, repository)


}