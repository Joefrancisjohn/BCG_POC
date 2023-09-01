package com.example.myapplication.di

import com.example.myapplication.topnews.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

   /* @ContributesAndroidInjector(modules = [MainListFragmetBuildersModule::class]) // Where to apply the injection.
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeAddSearchActivity(): AddSearchActivity*/
}