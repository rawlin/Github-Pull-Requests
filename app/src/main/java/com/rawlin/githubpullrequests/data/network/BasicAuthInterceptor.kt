package com.rawlin.githubpullrequests.data.network

import com.rawlin.githubpullrequests.BuildConfig
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class BasicAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val credentials = Credentials.basic(BuildConfig.user, BuildConfig.apiKey)
        val authenticatedRequest: Request = chain.request().newBuilder()
            .header("Authorization", credentials).build()
        return chain.proceed(authenticatedRequest)
    }
}