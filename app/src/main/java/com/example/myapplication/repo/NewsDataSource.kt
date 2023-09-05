package com.example.myapplication.repo

import com.example.myapplication.models.NetworkResult
import com.example.myapplication.models.TopStories

interface NewsDataSource {
    suspend fun getTasks(): NetworkResult<TopStories>
}