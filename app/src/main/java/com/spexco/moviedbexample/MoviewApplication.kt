package com.spexco.moviedbexample

import com.spexco.moviedbexample.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MoviewApplication : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }

    private val appComponent = DaggerAppComponent.builder().application(this).build()


    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}