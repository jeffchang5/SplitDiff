package io.jeffchang.splitdiff.ui.gitdiff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import io.jeffchang.splitdiff.R
import io.jeffchang.splitdiff.ui.gitdiff.viewmodel.PullRequestViewModel
import javax.inject.Inject

class PullRequestFragment: DaggerFragment() {

    @Inject
    lateinit var pullRequestViewModel: PullRequestViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_pull_request, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}