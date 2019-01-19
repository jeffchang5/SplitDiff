package io.jeffchang.splitdiff.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.jeffchang.splitdiff.common.di.ViewModelKey
import io.jeffchang.splitdiff.common.kt.ViewModelFactory
import io.jeffchang.splitdiff.ui.pullrequests.viewmodel.PullRequestViewModel

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PullRequestViewModel::class)
    abstract fun bindWordSearchViewModel(pullRequestViewModel: PullRequestViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}