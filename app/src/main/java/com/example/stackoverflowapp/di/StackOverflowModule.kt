package com.example.stackoverflowapp.di

import com.example.stackoverflowapp.api.ApiConstants.BASE_URL
import com.example.stackoverflowapp.api.StackOverflowApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object StackOverflowModule {

    @Provides
    @Singleton
    fun provideStackOverflowApi(builder:Retrofit.Builder):StackOverflowApi{
        return builder.baseUrl(BASE_URL)
            .build()
            .create(StackOverflowApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit.Builder{
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
    }
}