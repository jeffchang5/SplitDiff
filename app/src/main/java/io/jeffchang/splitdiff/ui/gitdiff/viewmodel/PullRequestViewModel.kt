package io.jeffchang.splitdiff.ui.gitdiff.viewmodel

import androidx.lifecycle.ViewModel
import io.jeffchang.splitdiff.ui.gitdiff.interactor.PullRequestInteractor
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PullRequestViewModel @Inject constructor(
        private val pullRequestInteractor: PullRequestInteractor): ViewModel() {

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}