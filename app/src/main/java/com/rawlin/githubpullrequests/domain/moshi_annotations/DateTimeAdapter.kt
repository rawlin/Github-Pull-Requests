package com.rawlin.githubpullrequests.domain.moshi_annotations

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateTimeAdapter {

    private val format: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH).also {
        it.timeZone = TimeZone.getTimeZone("UTC")
    }

    @ToJson
    fun toJson(@DateTime timeInMillis: Long): String {
        return format.format(Date(timeInMillis))
    }

    @FromJson
    @DateTime
    fun fromJson(formattedTime: String): Long? {
        return format.parse(formattedTime)?.time
    }

}