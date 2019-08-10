package com.jueggs.andutils.aac

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren

abstract class BaseViewModel<TViewState>(initialState: TViewState) : ViewModel(), CoroutineScope {
    private val job = Job()

    override val coroutineContext = job + Dispatchers.IO
    val viewStateStore = ViewStateStore(initialState)

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
        viewStateStore.dispose()
    }
}