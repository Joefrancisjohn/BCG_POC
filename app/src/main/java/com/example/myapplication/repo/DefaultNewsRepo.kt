package com.example.myapplication.repo

import com.example.myapplication.di.AppModule
import com.example.myapplication.models.NetworkResult
import com.example.myapplication.models.TopStories
import com.example.myapplication.repo.local.NewsDataSourceLocal
import com.example.myapplication.repo.remote.NewsDataSourceRemote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultNewsRepo @Inject constructor(
    @AppModule.NewsDataSourceRemote private val newsDataSourceRemote: NewsDataSource,
    @AppModule.NewsDataSourceLocal private val newsDataSourceLocal: NewsDataSource,
) : NewsRepository {
    override suspend fun getTasks(forceUpdate: Boolean): NetworkResult<TopStories> {
        val output: NetworkResult<TopStories> = if (forceUpdate) {
            newsDataSourceRemote.getTasks()
        } else {
            newsDataSourceLocal.getTasks() //dummy call
            NetworkResult.Error(3, "DB DATA")
        }
        return output
    }
}