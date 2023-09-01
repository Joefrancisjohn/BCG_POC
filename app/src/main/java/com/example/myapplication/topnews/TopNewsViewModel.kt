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

class TopNewsViewModel @Inject constructor(private val newsDataRepository: NewsDataRepositoryRemote) : ViewModel()  {

    var response: MutableLiveData<NetworkResult<TopStories>> = MutableLiveData()

    val data: LiveData<List<Result>>
        get() = _data
    private val _data = MutableLiveData<List<Result>>(emptyList())

    fun getTopNews(){
        response.value = NetworkResult.Loading()
        viewModelScope.launch {
            response.value = newsDataRepository.getNewsFromNW()
            /*when(val networkResult  = newsDataRepository.getNewsFromNW()) {
                is NetworkResult.Success -> {
                    println("Joe_Tag in VIEW Success : ${networkResult.data.copyright}")

                   // response.value = networkResult.data

                }
                is NetworkResult.Error -> {
                    println("Joe_Tag in VIEW Error : ${networkResult.message}")

                }
                is NetworkResult.Loading -> {
                    println("Joe_Tag in VIEW LOADING SCREEN ")
                }
                is NetworkResult.Exception -> {
                    println("Joe_Tag in VIEW Exception ${networkResult.e.message} ")
                }
            }*/
        }
    }
}