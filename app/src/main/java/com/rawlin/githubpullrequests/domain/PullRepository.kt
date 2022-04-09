package com.rawlin.githubpullrequests.domain

import com.rawlin.githubpullrequests.domain.models.AllPullRequests
import dagger.Provides
import dagger.hilt.EntryPoint
import retrofit2.Response

interface PullRepository {
    suspend fun getAllPullRequests(): Resource<AllPullRequests>
}
