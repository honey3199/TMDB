package com.example.mymovielibrary.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymovielibrary.scope.ViewModelKey
import com.example.mymovielibrary.ui.descriptionPage.DetailPageViewModel
import com.example.mymovielibrary.ui.homePage.HomePageViewModel
import com.example.mymovielibrary.viewModelFactory.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelBuilderModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomePageViewModel::class)
    fun contributeHomePageViewModel(homePageViewModel: HomePageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailPageViewModel::class)
    fun contributeDetailPageViewModel(detailPageViewModel: DetailPageViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
