package io.jeffchang.splitdiff.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.jeffchang.splitdiff.ui.pullrequests.PullRequestListFragment

@Suppress("unused")
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeWordSearchFragment(): PullRequestListFragment

}