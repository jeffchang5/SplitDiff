package io.jeffchang.splitdiff.ui.pullrequests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import io.jeffchang.splitdiff.R
import io.jeffchang.splitdiff.ui.common.LineItemDecoration
import io.jeffchang.splitdiff.ui.gitdiff.adapter.PullRequestRecyclerViewAdapter
import io.jeffchang.splitdiff.ui.gitdiff.viewmodel.PullRequestViewModel
import kotlinx.android.synthetic.main.fragment_pull_request_list.*
import javax.inject.Inject

class PullRequestListFragment: DaggerFragment() {

    @Inject
    lateinit var pullRequestViewModel: PullRequestViewModel

    private val pullRequestRecyclerViewAdapter by lazy {
        PullRequestRecyclerViewAdapter()
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
        pullRequestViewModel.getPullRequests()
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

}