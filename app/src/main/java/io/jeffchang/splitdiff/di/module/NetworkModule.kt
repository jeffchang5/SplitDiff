package io.jeffchang.splitdiff.di.module

import android.app.Application
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.github.stkent.githubdiffparser.GitHubDiffParser
import dagger.Module
import dagger.Provides
import io.jeffchang.splitdiff.BuildConfig
import io.jeffchang.splitdiff.data.service.GitDiffService
import io.jeffchang.splitdiff.data.service.PullRequestService
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule(
        private var baseUrl: String) {

    @Provides
    @Singleton
    internal fun provideHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    internal fun provideOkhttpClient(cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder()

        if (BuildConfig.DEBUG)
            client.addInterceptor(HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(StethoInterceptor())

        return client
                .cache(cache)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    internal fun providePullRequestService(retrofit: Retrofit) =
            retrofit.create(PullRequestService::class.java)


    @Provides
    @Singleton
    internal fun provideGitDiffService(retrofit: Retrofit) =
            retrofit.create(GitDiffService::class.java)


    @Provides
    @Singleton
    internal fun provideGitHubDiffParser() = GitHubDiffParser()
}
