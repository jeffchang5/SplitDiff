package io.jeffchang.splitdiff.ui.gitdiff.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.jeffchang.splitdiff.R
import io.jeffchang.splitdiff.data.model.TextData
import io.jeffchang.splitdiff.data.model.pullrequest.PullRequest
import io.jeffchang.splitdiff.ui.gitdiff.interactor.PullRequestInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PullRequestViewModel @Inject constructor(
        private val pullRequestInteractor: PullRequestInteractor): ViewModel() {

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    private val _textDataLiveData = MutableLiveData<TextData?>()

    val textDataLiveData get() = _textDataLiveData

    private val _pullRequestLiveData = MutableLiveData<List<PullRequest>>()

    val pullRequestLiveData get() = _pullRequestLiveData

    fun getPullRequests() {
        textDataLiveData.value = TextData(R.string.loading)
        pullRequestInteractor.getPullRequests("jeffchang5", "SplitDiff")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("There are %s pull requests.", it.size)
                    if (it.isEmpty()) {
                        _textDataLiveData.value = TextData(R.string.pull_requests_empty)
                    } else {
                        _textDataLiveData.value = null
                        _pullRequestLiveData.value = it
                    }
                }, {
                    Timber.e(it)
                    textDataLiveData.value = TextData(R.string.error_failure)
                })
                .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}