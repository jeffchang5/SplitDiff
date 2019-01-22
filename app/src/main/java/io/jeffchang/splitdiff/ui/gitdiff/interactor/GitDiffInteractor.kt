package io.jeffchang.splitdiff.ui.gitdiff.interactor

import io.jeffchang.githubdiffparser.models.Line
import io.jeffchang.splitdiff.data.model.gitdiff.Content
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
                                val fromLines = ArrayList<Content>()
                                val toLines = ArrayList<Content>()
                                val hunkList = hunk.lines.map {
                                    when (it.lineType) {
                                        Line.LineType.FROM -> {
                                            fromLines += Content(it.content, Content.Type.CHANGE)
                                        }
                                        Line.LineType.NEUTRAL -> {
                                            fromLines += Content(it.content, Content.Type.SAME)
                                            toLines += Content(it.content, Content.Type.SAME)
                                        }
                                        Line.LineType.TO -> {
                                            toLines += Content(it.content, Content.Type.CHANGE)
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