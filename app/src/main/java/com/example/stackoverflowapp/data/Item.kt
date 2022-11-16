package com.example.stackoverflowapp.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item(
    @Json(name = "accepted_answer_id")
    val acceptedAnswerId: Int=0,
    @Json(name = "answer_count")
    val answerCount: Int=0,
    @Json(name = "content_license")
    val contentLicense: String="",
    @Json(name = "creation_date")
    val creationDate: Int=0,
    @Json(name = "is_answered")
    val isAnswered: Boolean=true,
    @Json(name = "last_activity_date")
    val lastActivityDate: Int=0,
    @Json(name = "last_edit_date")
    val lastEditDate: Int = 0,
    @Json(name = "link")
    val link: String,
    @Json(name = "owner")
    val owner: Owner,
    @Json(name = "protected_date")
    val protectedDate: Int=0,
    @Json(name = "question_id")
    val questionId: Int=0,
    @Json(name = "score")
    val score: Int=0,
    @Json(name = "tags")
    val tags: List<String> = listOf(),
    @Json(name = "title")
    val title: String="",
    @Json(name = "view_count")
    val viewCount: Int=0
)