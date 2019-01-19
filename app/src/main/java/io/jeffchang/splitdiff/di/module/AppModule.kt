package io.jeffchang.splitdiff.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.jeffchang.splitdiff.data.service.PullRequestService
import io.jeffchang.splitdiff.data.service.PullRequestServiceImpl
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

}