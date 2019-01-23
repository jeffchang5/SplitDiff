package io.jeffchang.splitdiff.ui.pickrepo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import io.jeffchang.splitdiff.R
import io.jeffchang.splitdiff.ui.pullrequests.PullRequestListFragment.Companion.PULL_REQUEST_LIST_REPO_ARG
import io.jeffchang.splitdiff.ui.pullrequests.PullRequestListFragment.Companion.PULL_REQUEST_LIST_USERNAME_ARG
import kotlinx.android.synthetic.main.fragment_pick_repo.*

class PickRepoFragment: Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_pick_repo, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_pick_repo_next_button.setOnClickListener {
            val username = fragment_pick_repo_username_edit_text.text.toString().trim()
            val repo = fragment_pick_repo_repo_edit_text.text.toString().trim()
            if ((username.isEmpty()) or (repo.isEmpty())) {
                Toast.makeText(context, R.string.repo_empty, Toast.LENGTH_SHORT).show()
            } else {
                goToPullRequestListFragment(username, repo)
            }
        }
    }

    private fun goToPullRequestListFragment(username: String, repo: String) {
        val args = Bundle()
        args.putString(PULL_REQUEST_LIST_USERNAME_ARG, username)
        args.putString(PULL_REQUEST_LIST_REPO_ARG, repo)
        Navigation.findNavController(
                activity!!,
                R.id.activity_main_nav_host_fragment
        )
                .navigate(R.id.pullRequestFragment, args)
    }

}