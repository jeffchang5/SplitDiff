package io.jeffchang.splitdiff.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import io.jeffchang.splitdiff.SplitDiffApplication
import io.jeffchang.splitdiff.di.module.MainActivityModule
import io.jeffchang.splitdiff.di.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            AppModule::class,
            MainActivityModule::class
        ])
interface AppComponent : AndroidInjector<DaggerApplication> {

    override fun inject(instance: DaggerApplication)

    fun inject(application: SplitDiffApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}