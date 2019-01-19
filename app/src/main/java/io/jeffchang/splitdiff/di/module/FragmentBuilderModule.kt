package io.jeffchang.splitdiff.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.jeffchang.splitdiff.ui.gitdiff.PullRequestFragment

@Suppress("unused")
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeWordSearchFragment(): PullRequestFragment

}