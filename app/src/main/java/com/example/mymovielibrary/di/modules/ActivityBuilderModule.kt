package com.example.mymovielibrary.di.modules

import com.example.mymovielibrary.DetailPageActivity
import com.example.mymovielibrary.HomePageActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilderModule {

    @ContributesAndroidInjector
    fun contributeHomePageActivity():HomePageActivity

    @ContributesAndroidInjector
    fun contributeDetailPageActivity():DetailPageActivity
}
