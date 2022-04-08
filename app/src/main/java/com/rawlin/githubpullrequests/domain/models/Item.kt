package com.rawlin.githubpullrequests.domain.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item(
    @Json(name = "active_lock_reason")
    val activeLockReason: Any,
    val assignee: Any,
    val assignees: List<Assignee>,
    @Json(name = "author_association")
    val authorAssociation: String,
    val body: String,
    @Json(name = "closed_at")
    val closedAt: String,
    val comments: Int,
    @Json(name = "comments_url")
    val commentsUrl: String,
    @Json(name = "created_at")
    val createdAt: String,
    val draft: Boolean,
    @Json(name = "events_url")
    val eventsUrl: String,
    @Json(name = "html_url")
    val htmlUrl: String,
    val id: Int,
    val labels: List<Label>,
    @Json(name = "labels_url")
    val labelsUrl: String,
    val locked: Boolean,
    val milestone: Any,
    @Json(name = "node_id")
    val nodeId: String,
    val number: Int,
    @Json(name = "performed_via_github_app")
    val performedViaGithubApp: Any,
    @Json(name = "pull_request")
    val pullRequest: PullRequest,
    val reactions: Reactions,
    @Json(name = "repository_url")
    val repositoryUrl: String,
    val score: Int,
    val state: String,
    @Json(name = "timeline_url")
    val timelineUrl: String,
    val title: String,
    @Json(name = "updated_at")
    val updatedAt: String,
    val url: String,
    val user: User
)