package io.jeffchang.splitdiff.data.service

import io.jeffchang.splitdiff.data.model.pullrequest.PullRequest
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PullRequestService {

    @GET("/repos/{username}/{repo}/pulls")
    fun getPullRequests(
            @Path("username") username: String,
            @Path("repo") repository: String): Single<List<PullRequest>>

}