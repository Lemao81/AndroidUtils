package com.jueggs.andutils.base

import android.app.Application
import com.jueggs.andutils.Constant.LOG4K_PATTERN_ALL
import com.jueggs.andutils.Constant.TAG_DEBUG
import com.jueggs.andutils.e
import com.log4k.Level
import com.log4k.Log4k.add
import com.log4k.android.AndroidAppender
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

abstract class BaseApplication(private val isDebug: Boolean) : Application() {
    override fun onCreate() {
        super.onCreate()
        if (isDebug) {
            add(Level.Verbose, LOG4K_PATTERN_ALL, AndroidAppender(generateClassName = { "$TAG_DEBUG - ${it.substring(it.lastIndexOf('.') + 1)}" }))
        }
        Thread.setDefaultUncaughtExceptionHandler { _, exception ->
            e(exception)
            onUncaughtException(exception)
        }

        val koinModule = koinModule()
        val koinModules = koinModules()
        if (koinModule != null || koinModules != null && koinModules.any()) {
            startKoin {
                androidContext(this@BaseApplication)
                if (koinModule != null) {
                    modules(koinModule)
                } else if (koinModules != null)
                    modules(koinModules)
            }
        }

        initialize()
    }

    open fun onUncaughtException(exception: Throwable) {}

    open fun koinModule(): Module? = null

    open fun koinModules(): List<Module>? = null

    open fun initialize() {}
}