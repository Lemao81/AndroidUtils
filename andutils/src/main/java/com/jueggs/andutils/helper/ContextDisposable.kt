package com.jueggs.andutils.helper

import android.content.Context
import com.jueggs.andutils.interfaces.Disposable

abstract class ContextDisposable(protected var context: Context?) : Disposable {
    init {
        checkNotNull(context)
    }

    override fun dispose() {
        context = null
    }
}