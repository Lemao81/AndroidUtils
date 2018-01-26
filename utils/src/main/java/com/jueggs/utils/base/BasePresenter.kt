package com.jueggs.utils.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.FragmentActivity

abstract class BasePresenter<TView : BaseView> : LifecycleObserver {
    var activity: FragmentActivity? = null
    lateinit var view: TView
    lateinit var ctx: Context

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() {
        checkNotNull(view)
        view.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {
    }

    fun resString(@StringRes resId: Int) = ctx.getString(resId)

    abstract fun viewStub(): TView

    open fun getExtras(intent: Intent) {}
    open fun getArguments(bundle: Bundle) {}
    open fun initialize() {}
    open fun initializeViews() {}
}