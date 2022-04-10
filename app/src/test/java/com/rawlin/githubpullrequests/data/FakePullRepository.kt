package com.rawlin.githubpullrequests.data

import com.rawlin.githubpullrequests.data.FakeDataSource.dummyData
import com.rawlin.githubpullrequests.data.FakeDataSource.dummyEmptyData
import com.rawlin.githubpullrequests.data.FakeDataSource.prs
import com.rawlin.githubpullrequests.domain.PullRepository
import com.rawlin.githubpullrequests.domain.Resource
import com.rawlin.githubpullrequests.domain.models.AllPullRequests
import com.rawlin.githubpullrequests.domain.models.PullRequest
import com.rawlin.githubpullrequests.domain.models.User
import junit.framework.TestCase
import retrofit2.Response

class FakePullRepository : PullRepository {

    var testConditions: TestConditions = TestConditions.DEFAULT

    override suspend fun getAllPullRequests(): Resource<AllPullRequests> {
        return when (testConditions) {
            TestConditions.EMPTY_LIST -> Resource.Success(dummyEmptyData)
            TestConditions.BAD_REQUEST -> Resource.Error("Something went wrong")
            TestConditions.DEFAULT -> Resource.Success(dummyData)
        }
    }
}

enum class TestConditions {
    EMPTY_LIST, BAD_REQUEST, DEFAULT
}