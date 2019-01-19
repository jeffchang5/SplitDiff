package io.jeffchang.splitdiff

import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerApplication
import io.jeffchang.splitdiff.di.component.DaggerAppComponent
import timber.log.Timber

class SplitDiffApplication: DaggerApplication(), HasActivityInjector {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        // Initializes Timber
        Timber.uprootAll()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        val appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()
        appComponent.inject(this)
        return appComponent
    }

}