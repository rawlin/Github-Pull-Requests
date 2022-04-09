package com.rawlin.githubpullrequests.data

import com.rawlin.githubpullrequests.data.network.GithubAPI
import com.rawlin.githubpullrequests.domain.PullRepository
import com.rawlin.githubpullrequests.domain.Resource
import com.rawlin.githubpullrequests.domain.makeSafeApiCall
import com.rawlin.githubpullrequests.domain.models.AllPullRequests
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PullRepositoryImpl @Inject constructor(
    private val githubAPI: GithubAPI
): PullRepository {

    override suspend fun getAllPullRequests(): Resource<AllPullRequests> = withContext(Dispatchers.IO) {

        makeSafeApiCall {
            githubAPI.getPullRequest()
        }
    }
}
