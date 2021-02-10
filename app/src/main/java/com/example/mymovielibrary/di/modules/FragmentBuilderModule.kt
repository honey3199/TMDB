package com.example.mymovielibrary.di.modules

import com.example.mymovielibrary.ui.descriptionPage.DetailPageFragment
import com.example.mymovielibrary.ui.homePage.HomePageFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilderModule {
    @ContributesAndroidInjector
    fun contributeHomePageFragment():HomePageFragment

    @ContributesAndroidInjector
    fun contributeDetailPageFragment():DetailPageFragment
}
