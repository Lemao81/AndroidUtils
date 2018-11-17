package com.jueggs.andutils.aac

sealed class StateEvent<TState>(val action: TState.() -> TState)

class Trigger<TState>(action: TState.() -> TState) : StateEvent<TState>(action)

class Alter<TState>(action: TState.() -> TState) : StateEvent<TState>(action)