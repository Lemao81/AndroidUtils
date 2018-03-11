package com.jueggs.andutils.base

import android.app.*
import com.jueggs.andutils.util.logUnhandledException
import dagger.android.*
import javax.inject.Inject

abstract class BaseApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler { _, throwable -> logUnhandledException(throwable) }
        initialize()
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    abstract fun initialize()
}