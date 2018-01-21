package com.jueggs.utils.base

import android.content.Context
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
        if (arguments != null)
            presenter().getArguments(arguments!!)
        initialize()
    }

    abstract fun inject(): Unit?
    abstract fun presenter(): BaseFragmentPresenter<TView>
    abstract fun self(): TView
    open fun initialize() {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(layout(), container, false)
    abstract fun layout(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter().initializeViews()
        initializeViews()
        setListeners()
    }

    open fun initializeViews() {}
    open fun setListeners() {}

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        checkNotNull(this.context)
        ctx = this.context!!
        presenter().ctx = this.context!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter().activity = activity

        if (savedInstanceState == null)
            onInitialStart()
        else
            restoreState(savedInstanceState)
    }

    open fun onInitialStart() {}
    open fun restoreState(savedInstanceState: Bundle) {}

    override fun onDetach() {
        ctx = context!!.applicationContext
        presenter().view = presenter().viewStub()
        presenter().activity = null
        presenter().ctx = context!!.applicationContext
        super.onDetach()
    }
}