package io.jeffchang.splitdiff.ui.gitdiff.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.jeffchang.splitdiff.data.model.pullrequest.PullRequest
import io.jeffchang.splitdiff.ui.pullrequests.widget.PullRequestItem

class PullRequestRecyclerViewAdapter: ListAdapter<
        PullRequest,
        PullRequestRecyclerViewAdapter.PullRequestViewHolder>(PullRequestCallback()) {

    var onPullRequestClickedListener: OnPullRequestClickedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val viewHolder = PullRequestViewHolder(PullRequestItem(parent.context))
        viewHolder.itemView.setOnClickListener {
            val pullRequest = getItem(viewHolder.adapterPosition)
            onPullRequestClickedListener?.onPullRequestClicked(pullRequest)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        val pullRequest = getItem(position)
        holder.bind(pullRequest)
    }

    inner class PullRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(pullRequest: PullRequest) {
            (itemView as PullRequestItem).apply {
                this.author = pullRequest.user.login
                this.title = pullRequest.title
            }
        }
    }

    internal class PullRequestCallback: DiffUtil.ItemCallback<PullRequest>() {

        override fun areItemsTheSame(oldItem: PullRequest, newItem: PullRequest) =
                oldItem == newItem

        override fun areContentsTheSame(oldItem: PullRequest, newItem: PullRequest) =
                oldItem == newItem
    }

    interface OnPullRequestClickedListener {

        fun onPullRequestClicked(pullRequest: PullRequest)

    }

}