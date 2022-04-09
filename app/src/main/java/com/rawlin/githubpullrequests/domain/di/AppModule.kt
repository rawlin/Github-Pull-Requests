package com.rawlin.githubpullrequests.domain.di

import com.rawlin.githubpullrequests.data.network.BasicAuthInterceptor
import com.rawlin.githubpullrequests.data.network.GithubAPI
import com.rawlin.githubpullrequests.domain.Constants.BASE_URL
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(BasicAuthInterceptor())
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun getGithubService(retrofit: Retrofit): GithubAPI = retrofit.create(GithubAPI::class.java)
}
