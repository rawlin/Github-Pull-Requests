package com.rawlin.githubpullrequests.domain.models


import com.rawlin.githubpullrequests.domain.moshi_annotations.DateTime
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PullRequest(
    val body: String,
    @Json(name = "closed_at")
    val closedAt: String,
    @Json(name = "created_at")
    val createdAt: String,
    val id: Int,
    val title: String,
    @Json(name = "updated_at")
    val updatedAt: String,
    val user: User
)
