package com.jueggs.andutils.service

import android.content.Context
import com.jueggs.andutils.extension.isNetworkConnected
import com.jueggs.andutils.interfaces.Network
import com.jueggs.andutils.state.Connected
import com.jueggs.andutils.state.Disconnected
import com.jueggs.andutils.state.NetworkState

class AndroidNetwork(context: Context) : Network {
    private val appContext = context.applicationContext

    override val state: NetworkState
        get() = if (appContext.isNetworkConnected()) Connected else Disconnected
}