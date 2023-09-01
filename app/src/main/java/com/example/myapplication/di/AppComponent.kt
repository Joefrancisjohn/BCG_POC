package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.newsdetails.SecondFragment
import com.example.myapplication.topnews.FirstFragment
import com.example.myapplication.topnews.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

// Definition of a Dagger component
@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    // Classes that can be injected by this Component
    fun inject(fragment: FirstFragment)
    fun inject(fragment: SecondFragment)
    fun inject(activity: MainActivity)

}