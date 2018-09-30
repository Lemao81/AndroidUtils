package com.jueggs.andutils.util

import android.arch.lifecycle.*
import android.util.Log
import java.util.concurrent.atomic.AtomicBoolean


class SingleLiveEvent<T> : MutableLiveData<T>() {
    private val mPending = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        if (hasActiveObservers())
            Log.w("SingleLiveEvent", "Multiple observers registered but only one will be notified of changes.")

        super.observe(owner, Observer<T> { t ->
            if (mPending.compareAndSet(true, false))
                observer.onChanged(t)
        })
    }

    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    fun call() {
        value = null
    }
}