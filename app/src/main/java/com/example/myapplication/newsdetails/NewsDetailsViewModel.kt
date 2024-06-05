package com.example.myapplication.newsdetails

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

//@ActivityScoped
@HiltViewModel
class NewsDetailsViewModel @Inject constructor(): ViewModel()  {
}