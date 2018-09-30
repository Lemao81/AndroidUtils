package com.jueggs.andutils.base

import android.content.Context

abstract class BaseListenableFragment<TFragmentListener : Any> : BaseFragment() {
    protected var listener: TFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as TFragmentListener
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }
}