package com.rawlin.githubpullrequests.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.rawlin.githubpullrequests.MainCoroutineRule
import com.rawlin.githubpullrequests.data.FakeDataSource.dummyData
import com.rawlin.githubpullrequests.data.FakePullRepository
import com.rawlin.githubpullrequests.domain.Event
import com.rawlin.githubpullrequests.domain.Resource
import com.rawlin.githubpullrequests.getOrAwaitValueTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PullRequestViewModelTest {

    private lateinit var viewModel: PullRequestViewModel
    private lateinit var repo: FakePullRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        repo = FakePullRepository()
        viewModel = PullRequestViewModel(repo)
    }

    @Test
    fun `get all pull request from repo`() {
        viewModel.fireNetworkCall()

        val value = viewModel.allPullRequests.getOrAwaitValueTest()

        assertThat(value).isEqualTo(Event(Resource.Success(dummyData)))
    }
}