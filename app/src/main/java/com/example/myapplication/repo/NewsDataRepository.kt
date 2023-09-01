package com.example.myapplication.repo

import com.example.myapplication.models.TopStories

interface NewsDataRepository {
    suspend fun getTasks(forceUpdate: Boolean = false): Result<List<TopStories>>
}