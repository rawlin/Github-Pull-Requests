package com.rawlin.githubpullrequests.data.network

import com.rawlin.githubpullrequests.domain.Constants.CREATED
import com.rawlin.githubpullrequests.domain.Constants.DSC
import com.rawlin.githubpullrequests.domain.Constants.QUERY
import com.rawlin.githubpullrequests.domain.models.AllPullRequests
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubAPI {

    @GET("/search/issues")
    suspend fun getPullRequest(
        @Query("q", encoded = true) query: String = QUERY,
        @Query("sort") sortBy: String = CREATED,
        @Query("order") order: String = DSC
    ): Response<AllPullRequests>

}
