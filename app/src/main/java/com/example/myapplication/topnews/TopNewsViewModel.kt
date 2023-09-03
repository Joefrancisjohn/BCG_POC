package com.example.myapplication.topnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.NetworkResult
import com.example.myapplication.models.Result
import com.example.myapplication.models.TopStories
import com.example.myapplication.repo.NewsDataRepositoryRemote
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopNewsViewModel @Inject constructor(private val newsDataRepository: NewsDataRepositoryRemote) : ViewModel()  {

    var response: MutableLiveData<NetworkResult<TopStories>> = MutableLiveData()

    val data: LiveData<List<Result>>
        get() = _data
    private val _data = MutableLiveData<List<Result>>(emptyList())

    lateinit var tempResults : List<Result>

    fun getTopNews(){
        response.value = NetworkResult.Loading()
        viewModelScope.launch {
            response.value = newsDataRepository.getNewsFromNW()
        }
    }
}