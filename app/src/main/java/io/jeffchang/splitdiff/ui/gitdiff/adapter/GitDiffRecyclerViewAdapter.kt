package io.jeffchang.splitdiff.ui.gitdiff.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.jeffchang.splitdiff.data.model.GitDiff
import io.jeffchang.splitdiff.data.model.pullrequest.PullRequest
import io.jeffchang.splitdiff.ui.pullrequests.widget.PullRequestItem

class GitDiffRecyclerViewAdapter: ListAdapter<
        GitDiff,
        GitDiffRecyclerViewAdapter.GitDiffViewHolder>(GitDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitDiffViewHolder {
        val viewHolder = GitDiffViewHolder(PullRequestItem(parent.context))
        return viewHolder
    }

    override fun onBindViewHolder(holder: GitDiffViewHolder, position: Int) {
        val pullRequest = getItem(position)

    }

    inner class GitDiffViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    internal class GitDiffCallback: DiffUtil.ItemCallback<GitDiff>() {

        override fun areItemsTheSame(oldItem: GitDiff, newItem: GitDiff) =
                oldItem == newItem

        override fun areContentsTheSame(oldItem: GitDiff, newItem: GitDiff) =
                oldItem == newItem
    }

}