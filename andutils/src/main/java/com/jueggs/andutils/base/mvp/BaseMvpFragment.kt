package com.jueggs.andutils.base.mvp

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.jueggs.andutils.Constant.STATE_VIEWMODEL
import com.jueggs.andutils.extension.hideKeyboard
import com.jueggs.andutils.extension.longToast
import com.jueggs.andutils.isLollipopOrAboveUtil

abstract class BaseMvpFragment<TView : BaseView, TViewModel : Parcelable> : Fragment(), BaseView {
    protected lateinit var viewModel: TViewModel
    protected lateinit var ctx: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
        checkNotNull(this.context)
        ctx = this.context!!

        val presenter = presenter()
        presenter.ctx = this.context!!
        presenter.view = self()
    }

    open fun inject(): Unit? = null
    abstract fun presenter(): BaseMvpPresenter<TView, TViewModel>
    abstract fun self(): TView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    open fun initialize() {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(layout(), container, false)

    abstract fun layout(): Int

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel = viewModel()
            onInitialStart()
        } else {
            viewModel = checkNotNull(savedInstanceState.getParcelable(STATE_VIEWMODEL))
            restoreState(savedInstanceState)
        }

        val presenter = presenter()
        presenter.activity = activity
        presenter.viewModel = viewModel

        initializeViews(viewModel)
        setListeners()
        presenter.initialize()
    }

    abstract fun viewModel(): TViewModel
    open fun onInitialStart() {}
    open fun restoreState(savedInstanceState: Bundle) {}

    open fun initializeViews(model: TViewModel) {}
    open fun setListeners() {}

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        storeState(viewModel)
        outState.putParcelable(STATE_VIEWMODEL, viewModel)
    }

    open fun storeState(viewModel: TViewModel) {}

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val resId = optionsMenu()
        if (resId != null)
            inflater?.inflate(resId, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    open fun optionsMenu(): Int? = null

    override fun onOptionsItemSelected(item: MenuItem) = onMenuItemSelected(item.itemId) ?: super.onOptionsItemSelected(item)

    open fun onMenuItemSelected(id: Int): Boolean? = null

    //region baseview
    override fun finishView() {
        activity?.finish()
    }

    override fun finishViewAfterTransition() {
        if (isLollipopOrAboveUtil()) activity?.finishAfterTransition()
        else activity?.finish()
    }

    override fun showLongToast(msg: String): Toast? = longToast(msg)
    override fun showLongToast(resId: Int, vararg formatArgs: Any): Toast? {
        return if (formatArgs.any()) {
            val msg = getString(resId, formatArgs)
            longToast(msg)
        } else
            longToast(resId)
    }

    override fun hideSoftKeyboard() = activity?.hideKeyboard() ?: false
    //endregion

    override fun onDestroy() {
        ctx = context!!.applicationContext
        super.onDestroy()
    }
}