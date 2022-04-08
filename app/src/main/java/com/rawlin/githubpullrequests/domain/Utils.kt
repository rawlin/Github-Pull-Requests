package com.rawlin.githubpullrequests.domain

import retrofit2.Response

internal suspend fun <T : Any> makeSafeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {
    val response: Response<T>
    try {
        response = apiCall.invoke()
    } catch (t: Throwable) {
        t.printStackTrace()
        return Resource.Error(t.message ?: "Something went wrong")
    }
    return if (!response.isSuccessful) {
        Resource.Error("Error occurred : ${response.code()}")
    } else {
        return if (response.body() == null) {
            Resource.Error("Something went terribly wrong")
        } else {
            Resource.Success(response.body()!!)
        }
    }
}
