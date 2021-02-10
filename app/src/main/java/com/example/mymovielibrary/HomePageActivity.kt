package com.example.mymovielibrary

import android.os.Bundle
import com.example.mymovielibrary.databinding.ActivityMainBinding
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class HomePageActivity : DaggerAppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = activityInjector

    private lateinit var homePageBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homePageBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(homePageBinding.root)
        with(homePageBinding) {
            toolbarMainActivity.title = getString(R.string.app_name)
        }
    }
}
