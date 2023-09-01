package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.App
import com.example.myapplication.api.ApiInterface
import com.example.myapplication.repo.NewsDataRepository
import com.example.myapplication.repo.NewsDataRepositoryRemote
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(includes = [ViewModelsModule::class , NetworkModule::class])
class AppModule() {

    @Singleton // Annotation informs Dagger compiler that the instance should be created only once in the entire lifecycle of the application.
    @Provides // Annotation informs Dagger compiler that this method is the constructor for the Context return type.
    fun provideContext(app: App): Context = app // Using provide as a prefix is a common convention but not a requirement.

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class NewsRemoteDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class NewsLocalDataSource

   // @JvmStatic
    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

   // @JvmStatic
   /* @Singleton
    @NewsRemoteDataSource
    @Provides
    fun provideTasksRemoteDataSource(ioDispatcher: CoroutineDispatcher): NewsDataRepository {
        return NewsDataRepositoryRemote
    }

   // @JvmStatic
    @Singleton
    @NewsLocalDataSource
    @Provides
    fun provideTasksLocalDataSource(

        ioDispatcher: CoroutineDispatcher
    ): TasksDataSource {
        return TasksLocalDataSource(
            database.taskDao(), ioDispatcher
        )
    }*/

}