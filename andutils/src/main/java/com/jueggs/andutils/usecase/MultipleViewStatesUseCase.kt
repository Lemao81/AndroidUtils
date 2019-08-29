package com.jueggs.andutils.usecase

import com.jueggs.andutils.aac.Alter
import com.jueggs.andutils.aac.StateEvent
import com.jueggs.andutils.aac.Trigger
import kotlinx.coroutines.channels.ProducerScope

abstract class MultipleViewStatesUseCase<TViewState> {
    private var producerScope: ProducerScope<StateEvent<TViewState>>? = null

    operator fun invoke(): suspend ProducerScope<StateEvent<TViewState>>.() -> Unit = {
        producerScope = this
        execute()
        producerScope = null
    }

    protected suspend fun triggerViewState(action: TViewState.() -> TViewState) = producerScope?.send(Trigger(action))

    protected suspend fun alterViewState(action: TViewState.() -> TViewState) = producerScope?.send(Alter(action))

    abstract suspend fun execute()
}