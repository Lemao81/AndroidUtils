package com.jueggs.andutils.state

sealed class NetworkState

object Connected : NetworkState()
object Disconnected : NetworkState()