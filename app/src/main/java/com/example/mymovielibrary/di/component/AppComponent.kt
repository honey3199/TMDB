package com.example.mymovielibrary.di.component

import android.app.Application
import com.example.mymovielibrary.MovieApplication
import com.example.mymovielibrary.di.modules.ActivityBuilderModule
import com.example.mymovielibrary.di.modules.AppModule
import com.example.mymovielibrary.di.modules.FragmentBuilderModule
import com.example.mymovielibrary.di.modules.NetworkModule
import com.example.mymovielibrary.di.modules.RepositoryModule
import com.example.mymovielibrary.di.modules.ViewModelBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        FragmentBuilderModule::class,
        ViewModelBuilderModule::class,
        RepositoryModule::class,
        NetworkModule::class,
        AppModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(movieApplication: MovieApplication)
}