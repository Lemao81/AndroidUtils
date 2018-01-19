package com.jueggs.utils.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment : Fragment() {
    lateinit var ctx: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
    }

    open fun injectDependencies(): Unit? {
        return null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(getLayout(), container, false)

    abstract fun getLayout(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializePresenter()
        initializeComponents()
        initializeListeners()
    }

    abstract fun initializePresenter()
    open fun initializeComponents() {}
    open fun initializeListeners() {}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState == null)
            onInitialStart()
        else
            restoreState(savedInstanceState)
    }

    open fun onInitialStart() {}
    open fun restoreState(savedInstanceState: Bundle) {}

    override fun onStart() {
        super.onStart()
        checkNotNull(context)
        ctx = context!!
    }

    override fun onStop() {
        super.onStop()
        ctx = context!!.applicationContext
    }
}