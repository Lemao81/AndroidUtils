package com.jueggs.utils.base

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.Toast
import com.jueggs.utils.R
import com.jueggs.utils.STATE_VIEWMODEL
import com.jueggs.utils.extension.hideSoftKeyboardExt
import com.jueggs.utils.extension.setNavigationTransitions
import com.jueggs.utils.isLollipopOrAboveUtil
import org.jetbrains.anko.longToast

abstract class BaseActivity<TView : BaseView, TViewModel : Parcelable> : AppCompatActivity(), BaseView {
    protected lateinit var viewModel: TViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout())
        inject()
        initialize()
        setupToolbar()

        if (savedInstanceState == null) {
            viewModel = viewModel()
            onInitialStart()
        } else {
            viewModel = savedInstanceState.getParcelable(STATE_VIEWMODEL)
            restoreState(savedInstanceState)
        }

        val presenter = presenter()
        presenter.view = self()
        presenter.ctx = this
        presenter.activity = this
        presenter.viewModel = viewModel

        initializeViews(viewModel)
        setListeners()
        presenter.initialize()

        setNavigationTransitions(getEnterTransition(), getExitTransition(), getReenterTransition(), getReturnTransition())
    }

    abstract fun layout(): Int
    open fun inject(): Unit? = null
    open fun initialize() {}

    private fun setupToolbar() {
        val toolbar = toolbar()
        if (toolbar != null && toolbar is Toolbar) {
            setSupportActionBar(toolbar)
            supportActionBar?.title = getString(toolbarTitle())
            if (shallToolbarNavigateBack()) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setDisplayShowHomeEnabled(true)
            }
        }
    }

    abstract fun toolbar(): View?
    open fun toolbarTitle(): Int = R.string.empty_string
    open fun optionsMenu(): Int? = null
    open fun shallToolbarNavigateBack(): Boolean = true

    abstract fun presenter(): BasePresenter<TView, TViewModel>
    abstract fun self(): TView

    abstract fun viewModel(): TViewModel
    open fun onInitialStart() {}
    open fun restoreState(savedInstanceState: Bundle) {}

    open fun initializeViews(model: TViewModel) {}
    open fun setListeners() {}

    open fun getEnterTransition(): Int? = R.transition.fade
    open fun getExitTransition(): Int? = R.transition.fade
    open fun getReenterTransition(): Int? = R.transition.fade
    open fun getReturnTransition(): Int? = R.transition.fade

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        storeState(viewModel)
        outState.putParcelable(STATE_VIEWMODEL, viewModel)
    }

    open fun storeState(viewModel: TViewModel) {}

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val resId = optionsMenu()
        if (resId != null) {
            menuInflater.inflate(resId, menu)
            return true
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = onMenuItemSelected(item.itemId) ?: super.onOptionsItemSelected(item)

    open fun onMenuItemSelected(id: Int): Boolean? = null

    //region baseview
    override fun finishView() = finish()

    override fun finishViewAfterTransition() {
        if (isLollipopOrAboveUtil()) finishAfterTransition()
        else finish()
    }

    override fun showLongToast(msg: String): Toast = longToast(msg)
    override fun showLongToast(resId: Int, vararg formatArgs: Any): Toast {
        return if (formatArgs.any()) {
            val msg = getString(resId, formatArgs)
            longToast(msg)
        } else
            longToast(resId)
    }

    override fun hideSoftKeyboard() = hideSoftKeyboardExt()
    //endregion

    override fun onSupportNavigateUp(): Boolean {
        if (shallToolbarNavigateBack()) onBackPressed()
        return super.onSupportNavigateUp()
    }
}