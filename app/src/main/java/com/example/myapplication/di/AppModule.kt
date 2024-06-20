package com.example.myapplication.di

import com.example.myapplication.repo.NewsDataSource
import com.example.myapplication.repo.remote.api.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.migration.DisableInstallInCheck
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
@DisableInstallInCheck
class AppModule() {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class NewsDataSourceRemote

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class NewsDataSourceLocal

   // @JvmStatic
    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

    //@JvmStatic
    @Singleton
    @NewsDataSourceRemote
    @Provides
    fun provideNewsDataSourceRemote(
        apiInterface: ApiInterface,
        ioDispatcher: CoroutineDispatcher): NewsDataSource {
        return com.example.myapplication.repo.remote.NewsDataSourceRemote (apiInterface,ioDispatcher)
    }

   // @JvmStatic
    @Singleton
    @NewsDataSourceLocal
    @Provides
    fun provideTasksLocalDataSource(): NewsDataSource {
        return com.example.myapplication.repo.local.NewsDataSourceLocal()
    }

}