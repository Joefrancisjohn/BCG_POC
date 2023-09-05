package com.example.myapplication.repo

import com.example.myapplication.models.NetworkResult
import com.example.myapplication.models.TopStories

interface NewsRepository {
    suspend fun getTasks(forceUpdate: Boolean = false): NetworkResult<TopStories>
}