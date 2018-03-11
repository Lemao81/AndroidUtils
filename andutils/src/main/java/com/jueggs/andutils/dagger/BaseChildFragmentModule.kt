package com.jueggs.andutils.dagger

import dagger.Module

@Module
abstract class BaseChildFragmentModule {
    companion object {
        const val CHILD_FRAGMENT = "CHILD_FRAGMENT"
    }
}