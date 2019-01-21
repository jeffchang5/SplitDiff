package io.jeffchang.splitdiff.data.service

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface GitDiffService {

    @Streaming
    @GET
    fun getDiffFile(@Url fileUrl: String): Single<ResponseBody>
}