package com.example.mymovielibrary

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class HomePageActivity : DaggerAppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = activityInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Toolbar>(R.id.toolbar_main_activity)?.title = getString(R.string.app_name)
    }
}
