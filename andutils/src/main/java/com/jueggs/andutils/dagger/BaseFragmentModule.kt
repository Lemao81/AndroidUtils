package com.jueggs.andutils.dagger

import android.support.v4.app.*
import com.jueggs.andutils.annotation.*
import dagger.*
import javax.inject.Named

@Module
abstract class BaseFragmentModule {
    @Module
    companion object {
        const val FRAGMENT = "FRAGMENT"
        const val CHILD_FRAGMENT_MANAGER = "CHILD_FRAGMENT_MANAGER"

        @Provides
        @PerFragment
        @Named(CHILD_FRAGMENT_MANAGER)
        @JvmStatic
        fun provideChildFragmentManager(@Named(FRAGMENT) fragment: Fragment): FragmentManager = fragment.childFragmentManager
    }
}