package com.rawlin.githubpullrequests.domain

import android.text.format.*
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

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

internal fun Long.toDateString(): String {
    return DateFormat.format("dd/MM/yyyy hh:mm", this).toString()
}

internal fun String.toDateString(): String {
    val format: java.text.DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH).also {
        it.timeZone = TimeZone.getTimeZone("UTC")
    }
    val date = format.parse(this)

    return DateFormat.format("dd/MM/yyyy hh:mm", date).toString()
}