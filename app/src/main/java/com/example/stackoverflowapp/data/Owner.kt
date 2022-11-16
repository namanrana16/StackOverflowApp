package com.example.stackoverflowapp.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Owner(
    @Json(name = "accept_rate")
    val acceptRate: Int=0,
    @Json(name = "display_name")
    val displayName: String="",
    @Json(name = "link")
    val link: String="",
    @Json(name = "profile_image")
    val profileImage: String="https://lh3.googleusercontent.com/a-/AOh14Gh4hdybGywmtC1dsHlL0CXfZ6iyxQ9kZBesUGqw=k-s128",
    @Json(name = "reputation")
    val reputation: Int=0,
    @Json(name = "user_id")
    val userId: Int=0,
    @Json(name = "user_type")
    val userType: String=""
)