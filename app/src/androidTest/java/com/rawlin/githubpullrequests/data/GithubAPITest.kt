package com.rawlin.githubpullrequests.data

import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.rawlin.githubpullrequests.data.network.GithubAPI
import kotlinx.coroutines.runBlocking
import org.junit.Test
import javax.inject.Inject

class GithubAPITest @Inject constructor(
    private val githubAPI: GithubAPI
) {

    @Test
    fun checkIfResponseIsOk() {
        runBlocking {
            val response = githubAPI.getPullRequest()
            print(response.body())
            assertThat(response.isSuccessful).isTrue()

        }
    }

    @Test
    fun checkIfResponseProperType() {

    }

}