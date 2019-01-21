package io.jeffchang.splitdiff.ui.gitdiff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import dagger.android.support.DaggerFragment
import io.jeffchang.splitdiff.R
import io.jeffchang.splitdiff.ui.gitdiff.viewmodel.GitDiffViewModel
import kotlinx.android.synthetic.main.fragment_git_diff.*
import javax.inject.Inject

class GitDiffFragment: DaggerFragment() {

    @Inject
    lateinit var gitDiffViewModel: GitDiffViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_git_diff, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeUi()
        arguments?.getString(GIT_DIFF_URL_ARG)?.let {
            gitDiffViewModel.getGitDiff(it)
        }
    }

    private fun subscribeUi() {
        gitDiffViewModel.textDataLiveData.observe(this, Observer {

        })
        gitDiffViewModel.gitDiffLiveData.observe(this, Observer {
            diffView.hunk = it.first().diffs.first().hunkList.first()
        })
    }

    companion object {

        const val GIT_DIFF_URL_ARG = "GIT_DIFF_URL_ARG"
    }

}