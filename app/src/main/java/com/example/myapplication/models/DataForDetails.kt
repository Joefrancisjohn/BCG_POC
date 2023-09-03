package com.example.myapplication.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataForDetails(
    val title: String = "no data",
    val abstract: String = "no data",
    val img_url: String = "https://placehold.jp/100x100.png?text=no_image",
    /*val data1: String,
    val data2: String,
    val data3: String*/
) : Parcelable
