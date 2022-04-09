package com.rawlin.githubpullrequests.domain.di

import com.rawlin.githubpullrequests.data.network.GithubAPI
import com.rawlin.githubpullrequests.domain.Constants.BASE_URL
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun getGithubService(retrofit: Retrofit): GithubAPI = retrofit.create(GithubAPI::class.java)
}
