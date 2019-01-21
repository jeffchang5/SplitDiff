package io.jeffchang.splitdiff.ui.gitdiff.repository

import com.github.stkent.githubdiffparser.GitHubDiffParser
import io.jeffchang.splitdiff.data.service.GitDiffService
import io.jeffchang.splitdiff.data.service.PullRequestService
import javax.inject.Inject

class GitDiffRepository @Inject constructor(
        private val gitDiffService: GitDiffService,
        private val gitHubDiffParser: GitHubDiffParser
) {

    fun getDifDiff(diffUrl: String) =
            gitDiffService.getDiffFile(diffUrl)
                    .map {
                        gitHubDiffParser.parse(it.byteStream())
                    }

}