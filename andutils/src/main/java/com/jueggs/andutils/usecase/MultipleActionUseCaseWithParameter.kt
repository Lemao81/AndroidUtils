package com.jueggs.andutils.usecase

import com.jueggs.andutils.AUtil.produceEvents
import com.jueggs.andutils.aac.StateEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.ReceiveChannel
import java.lang.Exception

@ExperimentalCoroutinesApi
abstract class MultipleActionUseCaseWithParameter<TParameter, TViewState> {
    operator fun invoke(param: TParameter): ReceiveChannel<StateEvent<TViewState>> = produceEvents {
        try {
            onTry(param).invoke(this)
        } catch (ex: Exception) {
            onCatch(ex).invoke(this)
        } finally {
            onFinally().invoke(this)
        }
    }

    abstract fun onTry(param: TParameter): suspend ProducerScope<StateEvent<TViewState>>.() -> Unit
    abstract fun onCatch(ex: Exception): suspend ProducerScope<StateEvent<TViewState>>.() -> Unit
    open fun onFinally(): suspend ProducerScope<StateEvent<TViewState>>.() -> Unit = {}
}