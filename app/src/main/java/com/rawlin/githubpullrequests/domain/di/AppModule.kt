package com.rawlin.githubpullrequests.domain.di

import com.rawlin.githubpullrequests.data.network.GithubAPI
import com.rawlin.githubpullrequests.domain.Constants.BASE_URL
import com.rawlin.githubpullrequests.domain.moshi_annotations.DateTimeAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(DateTimeAdapter())
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Singleton
    @Provides
    fun getGithubService(retrofit: Retrofit): GithubAPI = retrofit.create(GithubAPI::class.java)
}
