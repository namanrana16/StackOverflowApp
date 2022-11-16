package com.example.stackoverflowapp.api

import com.example.stackoverflowapp.data.StackOverflowModel
import retrofit2.http.GET

interface StackOverflowApi {

    @GET("2.2/questions?key=ZiXCZbWaOwnDgpVT9Hx8IA((&order=desc&sort=activity&site=stackoverflow")
    suspend fun getQuestions():StackOverflowModel
}