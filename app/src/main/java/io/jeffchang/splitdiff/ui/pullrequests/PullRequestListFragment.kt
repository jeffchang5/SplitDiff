package io.jeffchang.splitdiff.ui.pullrequests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import io.jeffchang.splitdiff.R
import io.jeffchang.splitdiff.data.model.pullrequest.PullRequest
import io.jeffchang.splitdiff.ui.common.LineItemDecoration
import io.jeffchang.splitdiff.ui.gitdiff.GitDiffFragment.Companion.GIT_DIFF_URL_ARG
import io.jeffchang.splitdiff.ui.pullrequests.adapter.PullRequestRecyclerViewAdapter
import io.jeffchang.splitdiff.ui.pullrequests.viewmodel.PullRequestViewModel
import kotlinx.android.synthetic.main.fragment_pull_request_list.*
import javax.inject.Inject

class PullRequestListFragment: DaggerFragment(), PullRequestRecyclerViewAdapter.OnPullRequestClickedListener {

    @Inject
    lateinit var pullRequestViewModel: PullRequestViewModel

    private val pullRequestRecyclerViewAdapter by lazy {
        val adapter = PullRequestRecyclerViewAdapter()
        adapter.onPullRequestClickedListener = this
        adapter
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_pull_request_list, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()
        initRecyclerView()

        arguments?.let {
            val userName = it.getString(PULL_REQUEST_LIST_USERNAME_ARG)
                    ?: throw RuntimeException("Pull Request Username should not be null")
            val repo = it.getString(PULL_REQUEST_LIST_REPO_ARG)
                    ?: throw RuntimeException("Pull Request Repo should not be null")
            pullRequestViewModel.getPullRequests(userName, repo)
        }
    }

    private fun initRecyclerView() {
        fragment_pull_request_recyclerview.layoutManager = LinearLayoutManager(context)
        fragment_pull_request_recyclerview.addItemDecoration(LineItemDecoration(context))
        fragment_pull_request_recyclerview.adapter = pullRequestRecyclerViewAdapter
    }

    private fun subscribeUi() {
        pullRequestViewModel.textDataLiveData.observe(this, Observer {
            fragment_pull_request_loading_view.textData = it
        })
        pullRequestViewModel.pullRequestLiveData.observe(this, Observer {
            pullRequestRecyclerViewAdapter.submitList(it)
        })
    }

    override fun onPullRequestClicked(pullRequest: PullRequest) {
        val args = Bundle()
        args.putString(GIT_DIFF_URL_ARG, pullRequest.diff_url)
        findNavController(
                activity!!,
                R.id.activity_main_nav_host_fragment
        )
                .navigate(R.id.gitDiffFragment, args)
    }

    companion object {

        const val PULL_REQUEST_LIST_USERNAME_ARG = "PULL_REQUEST_LIST_USERNAME_ARG"

        const val PULL_REQUEST_LIST_REPO_ARG = "PULL_REQUEST_LIST_REPO_ARG"

    }
}