package com.jueggs.andutils.aac

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class BaseAndroidViewModel<TViewState>(application: Application, initialState: TViewState) : AndroidViewModel(application) {
    val viewStateStore = ViewStateStore(initialState)
}