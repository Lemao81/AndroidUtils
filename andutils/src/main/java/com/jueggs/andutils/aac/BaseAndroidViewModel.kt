package com.jueggs.andutils.aac

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseAndroidViewModel<TViewState>(application: Application, initialState: TViewState) : AndroidViewModel(application), CoroutineScope {
    private val job = Job()

    override val coroutineContext = job + Dispatchers.IO
    val viewStateStore = ViewStateStore(initialState)
}