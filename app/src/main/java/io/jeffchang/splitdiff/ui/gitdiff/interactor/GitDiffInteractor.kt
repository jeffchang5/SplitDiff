package io.jeffchang.splitdiff.ui.gitdiff.interactor

import io.jeffchang.githubdiffparser.models.Line
import io.jeffchang.splitdiff.data.model.gitdiff.Diff
import io.jeffchang.splitdiff.data.model.gitdiff.FileDiff
import io.jeffchang.splitdiff.data.model.gitdiff.Hunk
import io.jeffchang.splitdiff.ui.gitdiff.repository.GitDiffRepository
import javax.inject.Inject

class GitDiffInteractor @Inject constructor(
        private val gitDiffRepository: GitDiffRepository) {

    fun getGitDiff(diffUrl: String) =
            gitDiffRepository.getDifDiff(diffUrl)
                    .map { diffList ->

                        diffList.map { diff ->
                            val fileDiff = diff.hunks.map { hunk ->
                                val fromLines = ArrayList<String>()
                                val toLines = ArrayList<String>()
                                val hunkList = hunk.lines.map {
                                    when (it.lineType) {
                                        Line.LineType.FROM -> {
                                            fromLines += it.content
                                        }
                                        Line.LineType.NEUTRAL -> {
                                            fromLines += it.content
                                            toLines += it.content
                                        }
                                        Line.LineType.TO -> {
                                            toLines += it.content
                                        }
                                    }
                                    // Takes the larger range.
                                    val maxRange =
                                            if (hunk.fromFileRange.lineCount > toLines.size) {
                                                hunk.fromFileRange
                                            } else {
                                                hunk.toFileRange
                                            }
                                    Hunk(fromLines, toLines, maxRange)
                                }
                                Diff(hunkList)
                            }
                            FileDiff(fileDiff)
                        }
                    }

}