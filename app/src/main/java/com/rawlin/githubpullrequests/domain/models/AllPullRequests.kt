package com.rawlin.githubpullrequests.domain.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AllPullRequests(
    @Json(name = "incomplete_results")
    val incompleteResults: Boolean,
    val items: List<Item>,
    @Json(name = "total_count")
    val totalCount: Int
)