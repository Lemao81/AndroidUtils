package com.jueggs.andutils.usecase

import com.jueggs.andutils.util.Action
import kotlinx.coroutines.experimental.channels.ReceiveChannel

interface MultipleActionUseCaseWithParameter<TParameter, TViewState> {
    fun execute(param: TParameter): ReceiveChannel<Action<TViewState>>
}