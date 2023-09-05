package com.example.myapplication.di

import com.example.myapplication.repo.DefaultNewsRepo
import com.example.myapplication.repo.NewsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModuleBinds {

    @Singleton
    @Binds
    abstract fun bindRepository(repo: DefaultNewsRepo): NewsRepository
}
