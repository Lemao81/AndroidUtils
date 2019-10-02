package com.jueggs.andutils.aac

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jueggs.jutils.usecase.Alter
import com.jueggs.jutils.usecase.Keep
import com.jueggs.jutils.usecase.StateEvent
import com.jueggs.jutils.usecase.Trigger
import com.log4k.d
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewStateStore<TViewState>(private val initialState: TViewState) : CoroutineScope {
    private val store = MutableLiveData<TViewState>()
    private val job = Job()

    override val coroutineContext = job + IO

    private fun state(): TViewState = store.value ?: initialState

    fun observe(owner: LifecycleOwner, observer: TViewState.() -> Unit) {
        store.removeObservers(owner)
        store.observe(owner, Observer { state ->
            state?.let { with(it, observer) }
        })
    }

    fun dispatch(vararg events: StateEvent<TViewState>) {
        launch { events.forEach { handleEvent(it) } }
    }

    fun _dispatch(vararg blocks: suspend (state: TViewState) -> StateEvent<TViewState>) {
        launch { blocks.forEach { handleEvent(it(state())) } }
    }

    fun dispatch(vararg deferreds: Deferred<StateEvent<TViewState>>) {
        launch { deferreds.forEach { handleEvent(it.await()) } }
    }

    fun dispatch(vararg channels: ReceiveChannel<StateEvent<TViewState>>) {
        launch { channels.forEach { channel -> channel.consumeEach { handleEvent(it) } } }
    }

    fun dispatch(vararg blocks: suspend ProducerScope<StateEvent<TViewState>>.() -> Unit) {
        launch {
            produce<StateEvent<TViewState>> { blocks.forEach { it.invoke(this) } }.consumeEach { handleEvent(it) }
        }
    }

    private suspend fun handleEvent(event: StateEvent<TViewState>) {
        when (event) {
            is Alter -> withContext(Main) {
                dispatchState(event.action(state()))
            }
            is Trigger -> withContext(Main) {
                val oldState = state()
                dispatchState(event.action(oldState))
                dispatchState(oldState)
            }
            is Keep -> Unit
        }
    }

    private fun dispatchState(state: TViewState) {
        store.value = state
        d(state.toString())
    }

    fun dispose() = coroutineContext.cancelChildren()
}