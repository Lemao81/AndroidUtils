package com.jueggs.andutils.aac

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@ObsoleteCoroutinesApi
class ViewStateStore<TViewState>(private val initialState: TViewState) : CoroutineScope {
    private val store = MutableLiveData<TViewState>()
    private val job = Job()

    override val coroutineContext = job + IO

    private fun state(): TViewState = store.value ?: initialState

    fun observe(owner: LifecycleOwner, observer: TViewState.() -> Unit) {
        store.observe(owner, Observer { state ->
            state?.let { with(it, observer) }
        })
    }

    fun dispatch(function: suspend () -> StateEvent<TViewState>) {
        launch { handleEvent(function()) }
    }

    fun dispatch(deferred: Deferred<StateEvent<TViewState>>) {
        launch { handleEvent(deferred.await()) }
    }

    fun dispatch(event: StateEvent<TViewState>) {
        launch { handleEvent(event) }
    }

    fun dispatch(vararg functions: suspend () -> StateEvent<TViewState>) {
        launch { functions.forEach { handleEvent(it()) } }
    }

    fun dispatch(channel: ReceiveChannel<StateEvent<TViewState>>) {
        launch { channel.consumeEach { handleEvent(it) } }
    }

    fun dispatch(vararg events: StateEvent<TViewState>) {
        launch { events.forEach { handleEvent(it) } }
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

    fun dispose() = coroutineContext.cancelChildren()
}