package com.example.myapplication.repo.local

import com.example.myapplication.models.NetworkResult
import com.example.myapplication.models.TopStories
import com.example.myapplication.repo.NewsDataSource
import javax.inject.Inject

class NewsDataSourceLocal @Inject constructor(): NewsDataSource {
    override suspend fun getTasks(): NetworkResult<TopStories> {
        TODO("Not yet implemented")
    }
}