package com.jueggs.andutils.aac

import android.arch.lifecycle.*
import com.jueggs.andutils.util.Action
import kotlinx.coroutines.experimental.Dispatchers.IO
import kotlinx.coroutines.experimental.Dispatchers.Main
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.channels.*
import kotlin.coroutines.experimental.CoroutineContext

class ViewStateStore<TViewState>(private val initialState: TViewState) : CoroutineScope {
    private val store = MutableLiveData<TViewState>()
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + IO

    private fun state(): TViewState = store.value ?: initialState

    fun observe(owner: LifecycleOwner, observer: (TViewState) -> Unit) = store.observe(owner, Observer { it?.let(observer) })

    fun dispatchAction(f: suspend () -> Action<TViewState>) {
        launch {
            val action = f()
            withContext(Main) {
                dispatchState(action(state()))
            }
        }
    }

    fun dispatchActions(channel: ReceiveChannel<Action<TViewState>>) {
        launch {
            channel.consumeEach { action ->
                withContext(Main) {
                    dispatchState(action(state()))
                }
            }
        }
    }

    private fun dispatchState(state: TViewState) {
        store.value = state
    }
}