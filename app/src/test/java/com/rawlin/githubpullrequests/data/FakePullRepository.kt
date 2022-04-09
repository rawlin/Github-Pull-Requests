package com.rawlin.githubpullrequests.data

import com.rawlin.githubpullrequests.data.FakeDataSource.dummyData
import com.rawlin.githubpullrequests.data.FakeDataSource.prs
import com.rawlin.githubpullrequests.domain.PullRepository
import com.rawlin.githubpullrequests.domain.Resource
import com.rawlin.githubpullrequests.domain.models.AllPullRequests
import com.rawlin.githubpullrequests.domain.models.PullRequest
import com.rawlin.githubpullrequests.domain.models.User
import junit.framework.TestCase
import retrofit2.Response

class FakePullRepository : PullRepository {



    override suspend fun getAllPullRequests(): Resource<AllPullRequests> {
        return Resource.Success(dummyData)
    }
}