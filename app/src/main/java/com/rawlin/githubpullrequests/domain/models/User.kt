package com.rawlin.githubpullrequests.domain.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "avatar_url")
    val avatarUrl: String,
    val id: Int,
    val login: String,
)
