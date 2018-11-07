package com.jueggs.andutils.provider

sealed class ProviderStatus
object Enabled : ProviderStatus()
object Disabled : ProviderStatus()