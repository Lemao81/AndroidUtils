package com.jueggs.andutils.usecase

import com.jueggs.andutils.aac.StateEvent
import kotlinx.coroutines.channels.ReceiveChannel

interface MultipleActionUseCaseWithParameter<TParameter, TViewState> {
    fun execute(param: TParameter): ReceiveChannel<StateEvent<TViewState>>
}