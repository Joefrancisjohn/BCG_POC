package com.example.myapplication.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.newsdetails.NewsDetailsViewModel
import com.example.myapplication.topnews.TopNewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    // We'd like to take this implementation of the ViewModel class and make it available in an injectable map with MainViewModel::class as a key to that map.
    @Binds
    @IntoMap
    @ViewModelKey(TopNewsViewModel::class) // We use a restriction on multibound map defined with @ViewModelKey annotation, and if don't need any, we should use @ClassKey annotation provided by Dagger.
    abstract fun bindMainViewModel(mainViewModel: TopNewsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsDetailsViewModel::class)
    abstract fun bindAddSearchViewModel(addSearchViewModel: NewsDetailsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}