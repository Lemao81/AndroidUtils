package com.jueggs.jutils.usecase

sealed class StateEvent<TState>() {
    lateinit var action: TState.() -> TState

    constructor(action: TState.() -> TState) : this() {
        this.action = action
    }
}

class Trigger<TState>(action: TState.() -> TState) : StateEvent<TState>(action)

class Alter<TState>(action: TState.() -> TState) : StateEvent<TState>(action)

class Keep<TState> : StateEvent<TState>()