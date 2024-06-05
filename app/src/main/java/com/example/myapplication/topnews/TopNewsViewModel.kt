package com.example.myapplication.topnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.NetworkResult
import com.example.myapplication.models.Result
import com.example.myapplication.models.TopStories
import com.example.myapplication.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
//Lycan import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.consumeAsFlow
import java.lang.Exception

//
@HiltViewModel
class TopNewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel()  {

    var response: MutableLiveData<NetworkResult<TopStories>> = MutableLiveData()


    val data: LiveData<List<Result>>
        get() = _data
    private val _data = MutableLiveData<List<Result>>(emptyList())

    lateinit var tempResults : List<Result>

    val userIntent = Channel<TopNewsIntent>(Channel.UNLIMITED)
    //Lycan
    /*var state = mutableStateOf<TopNewsState>(TopNewsState.Idle)
        private set*/


    init {
        getTopNews()
    }

    //Lycan
   /* private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect { collector ->
                when (collector) {
                    is TopNewsIntent.FetchTopNews -> fetchTopNews()
                }
            }
        }
    }*/

    //Lycan
   /* private fun fetchTopNews() {
        viewModelScope.launch {
            state.value = TopNewsState.Loading
            state.value = try {
                TopNewsState.TopStoriesMVI(newsRepository.getTasksMVI(true)!!)
            } catch (e: Exception) {
                TopNewsState.Error(e.localizedMessage)
            }
        }
    }*/
    fun getTopNews(){
        response.value = NetworkResult.Loading()
        viewModelScope.launch {
            response.value = newsRepository.getTasks(true)
        }
    }
}