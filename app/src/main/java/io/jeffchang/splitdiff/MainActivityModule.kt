package io.jeffchang.splitdiff

import dagger.Module

import dagger.android.ContributesAndroidInjector
import io.jeffchang.splitdiff.di.module.FragmentBuilderModule

@Suppress("unused")
@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun contributeMainActivity(): MainActivity

}