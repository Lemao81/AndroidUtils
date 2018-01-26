package com.jueggs.utils.base

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jueggs.utils.STATE_VIEWMODEL
import com.jueggs.utils.isLollipopOrAboveUtil
import org.jetbrains.anko.support.v4.longToast

abstract class BaseFragment<TView : BaseView, TViewModel : Parcelable> : Fragment(), BaseView {
    private lateinit var viewModel: TViewModel
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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter().activity = activity

        if (savedInstanceState == null) {
            viewModel = viewModel()
            onInitialStart()
        } else {
            viewModel = savedInstanceState.getParcelable(STATE_VIEWMODEL)
            restoreState(savedInstanceState)
        }

        initializeViews(viewModel)
        setListeners()
    }

    abstract fun viewModel(): TViewModel
    open fun onInitialStart() {}
    open fun restoreState(savedInstanceState: Bundle) {}

    open fun initializeViews(viewModel: TViewModel) {}
    open fun setListeners() {}

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        storeState(viewModel)
        outState.putParcelable(STATE_VIEWMODEL, viewModel)
    }

    open fun storeState(viewModel: TViewModel) {}

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