package com.jueggs.andutils.aac

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<TViewState>(initialState: TViewState) : ViewModel(), CoroutineScope {
    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    val viewStateStore = ViewStateStore(initialState)

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }
}