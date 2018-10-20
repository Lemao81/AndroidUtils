package com.jueggs.andutils.aac

import android.arch.lifecycle.*

open class LifeCycleStartStopObserver : LifecycleObserver {
    private var actionHandler: StartStopEventHandler? = null

    fun registerHandler(handler: StartStopEventHandler) {
        actionHandler = handler
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() = actionHandler?.onStart()

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() = actionHandler?.onStop()

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        actionHandler = null
    }
}