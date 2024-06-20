package com.example.myapplication.topnews

import com.example.myapplication.models.DetailsData

sealed class TopNewsIntent {

    object FetchTopNews: TopNewsIntent()
    data class GoToDetails(val detailsData: DetailsData) : TopNewsIntent()

}