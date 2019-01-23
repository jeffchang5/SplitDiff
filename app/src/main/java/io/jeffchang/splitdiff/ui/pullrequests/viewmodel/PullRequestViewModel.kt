package io.jeffchang.splitdiff.ui.pullrequests.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.jeffchang.splitdiff.R
import io.jeffchang.splitdiff.data.model.TextData
import io.jeffchang.splitdiff.data.model.pullrequest.PullRequest
import io.jeffchang.splitdiff.ui.pullrequests.interactor.PullRequestInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import timber.log.Timber
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

    fun getPullRequests(userName: String, repo: String) {
        textDataLiveData.value = TextData(R.string.loading)
        pullRequestInteractor.getPullRequests(userName, repo)
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
                }, { throwable ->
                    Timber.e(throwable)
                    (throwable as? HttpException)?.let {
                        if (it.code() == 404) {
                            textDataLiveData.value = TextData(R.string.repo_does_not_exist)
                        }
                    }
                    textDataLiveData.value = TextData(R.string.error_failure)
                })
                .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}