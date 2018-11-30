package com.jueggs.andutils.usecase

import com.jueggs.andutils.aac.StateEvent
import kotlinx.coroutines.channels.ReceiveChannel

interface MultipleActionUseCase<TViewState> {
    operator fun invoke(): ReceiveChannel<StateEvent<TViewState>>
}