package com.example.myapplication.di

import com.example.myapplication.repo.DefaultNewsRepo
import com.example.myapplication.repo.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModuleBinds {

    @Singleton
    @Binds
    abstract fun bindRepository(repo: DefaultNewsRepo): NewsRepository
}
