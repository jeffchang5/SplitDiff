package io.jeffchang.splitdiff.ui.gitdiff.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.jeffchang.githubdiffparser.models.Diff
import io.jeffchang.splitdiff.R
import io.jeffchang.splitdiff.data.model.TextData
import io.jeffchang.splitdiff.data.model.gitdiff.FileDiff
import io.jeffchang.splitdiff.ui.gitdiff.interactor.GitDiffInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


class GitDiffViewModel @Inject constructor(
        private val gitDiffInteractor: GitDiffInteractor): ViewModel() {

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    private val _textDataLiveData = MutableLiveData<TextData?>()

    val textDataLiveData get() = _textDataLiveData

    private val _gitDiffLiveData = MutableLiveData<List<FileDiff>>()

    val gitDiffLiveData get() = _gitDiffLiveData

    fun getGitDiff(diffUrl: String) {
        gitDiffInteractor.getGitDiff(diffUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("There are %s diffs.", it.size)
                    _gitDiffLiveData.value = it

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