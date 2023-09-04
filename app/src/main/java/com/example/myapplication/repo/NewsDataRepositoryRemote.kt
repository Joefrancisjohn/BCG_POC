package com.example.myapplication.repo

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.api.ApiInterface
import com.example.myapplication.models.NetworkResult
import com.example.myapplication.models.TopStories
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsDataRepositoryRemote @Inject constructor(
    private val apiInterface: ApiInterface,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getNewsFromNW(): NetworkResult<TopStories> {
        var output: NetworkResult<TopStories> = NetworkResult.Loading()
        // var output = "Init"
        withContext(ioDispatcher) {
            try {
                val response = apiInterface.getTopStories()
                if (response.isSuccessful) {
                    var json = Gson().toJson(response.body())
                    if (response.body()?.results?.size!! <= 0) {

                        println("Joe_Tag FETCH ERROR HAPPENED SIZE ZERO")
                    } else {
                        val result = response.body()?.copyright
                        output = NetworkResult.Success(response.body()!!)
                    }
                } else {
                    output =
                        NetworkResult.Error(code = response.code(), message = response.message())
                }
            } catch (Ex: Exception) {
                Ex.localizedMessage?.let { println("Joe_Tag FETCH Exception HAPPENED  $it") }
                output = NetworkResult.Exception(Ex)
            }
        }

        return output
    }
}