package com.jueggs.andutils.aac

import android.arch.lifecycle.*

open class LifeCycleStartStopObserver : LifecycleObserver {
    private var eventHandler: StartStopEventHandler? = null

    fun registerLifeCycle(lifecycle: Lifecycle) = lifecycle.addObserver(this)

    fun registerHandler(handler: StartStopEventHandler) {
        eventHandler = handler
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() = eventHandler?.onStart()

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() = eventHandler?.onStop()

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        eventHandler = null
    }

    fun test() {
    }
}

// singleton usage:
//object UniqueObserver : LifeCycleStartStopObserver()
//UniqueObserver.registerLifeCycle(lifeCycle) at lifecycleowner
//UniqueObserver.registerHandler(theHandler) at element implementing handler interface, wanting observe lifecycle
