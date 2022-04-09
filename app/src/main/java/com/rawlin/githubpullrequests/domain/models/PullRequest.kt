package com.rawlin.githubpullrequests.domain.models


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
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
): Parcelable
