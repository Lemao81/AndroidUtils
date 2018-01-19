package com.jueggs.utils.base

import android.content.Context
import android.net.http.SslCertificate.restoreState
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment<TView : BaseView> : Fragment() {
    protected lateinit var ctx: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        presenter().view = self()
    }

    abstract fun inject(): Unit?
    abstract fun presenter(): BasePresenter<TView>
    abstract fun self(): TView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(layout(), container, false)
    abstract fun layout(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeViews()
        setListeners()
    }

    open fun initializeViews() {}
    open fun setListeners() {}

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        checkNotNull(this.context)
        ctx = this.context!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState == null)
            onInitialStart()
        else
            restoreState(savedInstanceState)
    }

    open fun onInitialStart() {}
    open fun restoreState(savedInstanceState: Bundle) {}

    override fun onDetach() {
        ctx = context!!.applicationContext
        super.onDetach()
    }
}