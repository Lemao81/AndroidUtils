package com.jueggs.andutils.aac

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<TViewState>(initialState: TViewState) : ViewModel() {
    val viewStateStore = ViewStateStore(initialState)

    override fun onCleared() {
        super.onCleared()
        viewStateStore.dispose()
    }
}