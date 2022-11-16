package com.example.stackoverflowapp.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StackOverflowModel(
    @Json(name = "has_more")
    val hasMore: Boolean = true,
    @Json(name = "items")
    val items: List<Item>,
    @Json(name = "quota_max")
    val quotaMax: Int = 10000,
    @Json(name = "quota_remaining")
    val quotaRemaining: Int = 9999
)