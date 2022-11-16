package com.example.stackoverflowapp.repo

import com.example.stackoverflowapp.api.StackOverflowApi
import javax.inject.Inject

class StackOverflowRepo @Inject constructor( private val stackOverflowApi: StackOverflowApi) {

    suspend fun getQuestions()=stackOverflowApi.getQuestions()
}


