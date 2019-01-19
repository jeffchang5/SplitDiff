package io.jeffchang.splitdiff.ui.pickrepo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.jeffchang.splitdiff.R
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
            val username = fragment_pick_repo_username_edit_text.text.toString()
            val repo = fragment_pick_repo_repo_edit_text.text.toString()
        }

    }

}