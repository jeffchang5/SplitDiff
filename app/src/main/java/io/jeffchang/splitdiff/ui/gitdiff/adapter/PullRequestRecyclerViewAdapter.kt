package io.jeffchang.splitdiff.ui.gitdiff.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.jeffchang.splitdiff.data.model.pullrequest.PullRequest
import io.jeffchang.splitdiff.ui.gitdiff.widget.PullRequestItem

class PullRequestRecyclerViewAdapter: RecyclerView.Adapter<
        PullRequestRecyclerViewAdapter.PullRequestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            PullRequestViewHolder(PullRequestItem(parent.context))

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {

    }

    inner class PullRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(pullRequest: PullRequest) {

        }
    }

}