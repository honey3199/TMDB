package com.example.mymovielibrary.di.modules

import android.app.Application
import android.content.Context
import com.example.mymovielibrary.databse.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    companion object {
        @Provides
        fun provideDao(context: Context) = MovieDatabase.getDatabase(context).movieDao()
    }
}
