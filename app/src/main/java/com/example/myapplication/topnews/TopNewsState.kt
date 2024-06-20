package com.example.myapplication.topnews

import com.example.myapplication.models.DetailsData
import com.example.myapplication.models.TopStories

sealed class TopNewsState {

    object Idle: TopNewsState()
    object Loading: TopNewsState()
    data class TopStoriesMVI(val topStories: TopStories): TopNewsState()
    data class StoriesDetailsMVI(val detailsData: DetailsData): TopNewsState()
    data class Error(val error: String?): TopNewsState()

}