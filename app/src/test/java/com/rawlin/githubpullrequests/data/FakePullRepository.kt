package com.rawlin.githubpullrequests.data

import com.rawlin.githubpullrequests.domain.PullRepository
import com.rawlin.githubpullrequests.domain.Resource
import com.rawlin.githubpullrequests.domain.models.AllPullRequests
import junit.framework.TestCase
import retrofit2.Response

class FakePullRepository : PullRepository {
    override suspend fun getAllPullRequests(): Resource<AllPullRequests> {
        TODO("Not yet implemented")
    }
}