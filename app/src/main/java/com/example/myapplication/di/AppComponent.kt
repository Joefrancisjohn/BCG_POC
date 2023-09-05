package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.newsdetails.FragmentDetailedNews
import com.example.myapplication.topnews.FragmentTopNews
import com.example.myapplication.topnews.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

// Definition of a Dagger component
@Singleton
@Component(
    modules = [
        AppModule::class,
        AppModuleBinds::class
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
    fun inject(fragment: FragmentTopNews)
    fun inject(fragment: FragmentDetailedNews)
    fun inject(activity: MainActivity)

}