package com.jueggs.andutils.usecase

import com.jueggs.andutils.Util.produceEvents
import com.jueggs.andutils.aac.StateEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.ReceiveChannel

@ExperimentalCoroutinesApi
abstract class MultipleActionUseCaseWithParameter<TParameter, TViewState> {
    operator fun invoke(scope: CoroutineScope, param: TParameter): ReceiveChannel<StateEvent<TViewState>> = scope.produceEvents {
        runCatching { execute(param).invoke(this) }.onFailure { onFailure(it).invoke(this) }
        final().invoke(this)
    }

    abstract fun execute(param: TParameter): suspend ProducerScope<StateEvent<TViewState>>.() -> Unit
    abstract fun onFailure(th: Throwable): suspend ProducerScope<StateEvent<TViewState>>.() -> Unit
    open fun final(): suspend ProducerScope<StateEvent<TViewState>>.() -> Unit = {}
}