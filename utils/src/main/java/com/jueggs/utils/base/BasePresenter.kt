package com.jueggs.utils.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.os.Parcelable
import android.support.annotation.StringRes
import android.support.v4.app.FragmentActivity

abstract class BasePresenter<TView : BaseView, in TViewModel : Parcelable> : LifecycleObserver {
    private var viewModel: TViewModel? = null
    var activity: FragmentActivity? = null
    lateinit var view: TView
    lateinit var ctx: Context

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() {
        checkNotNull(view)
        view.lifecycle.addObserver(this)
    }

    open fun initialize(viewModel: TViewModel) {}

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
        dispose()
    }

    private fun dispose() {
        activity = null
        view = viewStub()
        ctx = ctx.applicationContext
    }

    abstract fun viewStub(): TView

    fun resString(@StringRes resId: Int) = ctx.getString(resId)
}