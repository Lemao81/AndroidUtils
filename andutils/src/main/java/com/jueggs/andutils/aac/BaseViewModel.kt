package com.jueggs.andutils.aac

import android.arch.lifecycle.ViewModel

abstract class BaseViewModel<TViewState>(initialState: TViewState) : ViewModel() {
    val viewStateStore = ViewStateStore(initialState)
}