package com.jueggs.andtestutils.util

import androidx.test.espresso.IdlingResource
import com.jueggs.andutils.test.CountingIdlingResource

object CountingSingletonIdlingResource : IdlingResource {
    override fun getName(): String = CountingSingletonIdlingResource::class.java.simpleName

    override fun isIdleNow() = CountingIdlingResource.isIdleNow()

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) =
        CountingIdlingResource.registerIdleTransitionCallback { callback?.onTransitionToIdle() }
}