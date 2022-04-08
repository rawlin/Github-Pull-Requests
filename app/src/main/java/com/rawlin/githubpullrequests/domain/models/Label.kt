package com.rawlin.githubpullrequests.domain.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Label(
    val color: String,
    val default: Boolean,
    val description: String,
    val id: Int,
    val name: String,
    @Json(name = "node_id")
    val nodeId: String,
    val url: String
)