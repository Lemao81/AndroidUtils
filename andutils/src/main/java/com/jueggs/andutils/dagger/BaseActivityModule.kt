package com.jueggs.andutils.dagger

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.jueggs.andutils.annotation.PerActivity
import dagger.*
import javax.inject.Named

@Module
abstract class BaseActivityModule {
    @Binds
    @PerActivity
    abstract fun bindActivityContext(activity: AppCompatActivity): Context

    @Module
    companion object {
        const val ACTIVITY_FRAGMENT_MANAGER = "ACTIVITY_FRAGMENT_MANAGER"

        @Provides
        @PerActivity
        @Named(ACTIVITY_FRAGMENT_MANAGER)
        @JvmStatic
        fun provideActivityFragmentManager(activity: AppCompatActivity): FragmentManager = activity.supportFragmentManager
    }
}