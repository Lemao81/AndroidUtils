package com.jueggs.andutils.aac

import android.app.Application
import android.arch.lifecycle.*

abstract class BaseAndroidViewModel<TViewState>(application: Application, initialState: TViewState) : AndroidViewModel(application) {
    val viewStateStore = ViewStateStore(initialState)
}