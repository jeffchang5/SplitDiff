package io.jeffchang.splitdiff.ui.pullrequests.repository

import io.jeffchang.splitdiff.data.service.PullRequestService
import javax.inject.Inject

class PullRequestRepository @Inject constructor(
        private val pullRequestService: PullRequestService
) {

    fun getPullRequests(username: String, repository: String) =
            pullRequestService.getPullRequests(username, repository)

}