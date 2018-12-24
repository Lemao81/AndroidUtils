package com.jueggs.andutils.usecase

import com.jueggs.andutils.Util.produceEvents
import com.jueggs.andutils.aac.StateEvent
import com.jueggs.andutils.e
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.ReceiveChannel

@ExperimentalCoroutinesApi
abstract class MultipleActionUseCase<TViewState> {
    operator fun invoke(scope: CoroutineScope): ReceiveChannel<StateEvent<TViewState>> = scope.produceEvents {
        runCatching { execute().invoke(this) }.onFailure {
            e(it)
            onFailure(it).invoke(this)
        }
        final().invoke(this)
    }

    abstract fun execute(): suspend ProducerScope<StateEvent<TViewState>>.() -> Unit
    abstract fun onFailure(th: Throwable): suspend ProducerScope<StateEvent<TViewState>>.() -> Unit
    open fun final(): suspend ProducerScope<StateEvent<TViewState>>.() -> Unit = {}
}