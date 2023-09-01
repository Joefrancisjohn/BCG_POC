package com.example.myapplication.api

import com.example.myapplication.models.TopStories
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {


    @GET("topstories/v2/world.json?api-key=RiTezICSAaZ5XpGZmatS198JEYwMiaBT")
    suspend fun getTopStories(): Response<TopStories>
}