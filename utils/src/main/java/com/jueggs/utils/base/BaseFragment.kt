package com.jueggs.utils.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jueggs.utils.isLollipopOrAboveUtil
import org.jetbrains.anko.support.v4.longToast

abstract class BaseFragment<TView : BaseView> : Fragment(), BaseView {
    protected lateinit var ctx: Context

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        inject()
        checkNotNull(this.context)
        ctx = this.context!!

        presenter().ctx = this.context!!
        presenter().view = self()
    }

    abstract fun inject(): Unit?
    abstract fun presenter(): BasePresenter<TView>
    abstract fun self(): TView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null)
            presenter().getArguments(arguments!!)
        initialize()
    }

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

    override fun finishView() {
        activity?.finish()
    }

    override fun finishViewAfterTransition() {
        if (isLollipopOrAboveUtil()) activity?.finishAfterTransition()
        else activity?.finish()
    }

    override fun showLongToast(msg: String): Toast = longToast(msg)
    override fun showLongToast(resId: Int): Toast = longToast(resId)

    override fun onDetach() {
        ctx = context!!.applicationContext
        presenter().view = presenter().viewStub()
        presenter().ctx = context!!.applicationContext
        presenter().activity = null
        super.onDetach()
    }
}