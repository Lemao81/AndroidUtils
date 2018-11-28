package com.jueggs.andutils.state

sealed class ProviderState

object Enabled : ProviderState()
object Disabled : ProviderState()