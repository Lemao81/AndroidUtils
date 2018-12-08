package com.jueggs.andutils.aac

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

@ObsoleteCoroutinesApi
class ViewStateStore<TViewState>(private val initialState: TViewState) : CoroutineScope {
    private val store = MutableLiveData<TViewState>()
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + IO

    private fun state(): TViewState = store.value ?: initialState

    fun observe(owner: LifecycleOwner, observer: TViewState.() -> Unit) {
        store.observe(owner, Observer { state ->
            state?.let { with(it, observer) }
        })
    }

    fun dispatchAction(func: suspend () -> StateEvent<TViewState>) {
        launch { handleEvent(func()) }
    }

    fun dispatchActions(vararg funcs: suspend () -> StateEvent<TViewState>) {
        launch {
            funcs.forEach { handleEvent(it()) }
        }
    }

    fun dispatchActions(channel: ReceiveChannel<StateEvent<TViewState>>) {
        launch { channel.consumeEach { handleEvent(it) } }
    }

    private suspend fun handleEvent(event: StateEvent<TViewState>) {
        withContext(Main) {
            when (event) {
                is Alter -> dispatchState(event.action(state()))
                is Trigger -> {
                    val oldState = state()
                    dispatchState(event.action(oldState))
                    dispatchState(oldState)
                }
            }
        }
    }

    private fun dispatchState(state: TViewState) {
        store.value = state
    }
}