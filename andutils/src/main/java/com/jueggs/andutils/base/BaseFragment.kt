package com.jueggs.andutils.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.jueggs.andutils.Util.postDelayed
import com.jueggs.andutils.extension.goneOrVisible
import com.jueggs.andutils.extension.visibleOrInvisible
import com.jueggs.andutils.interfaces.BackPressHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

abstract class BaseFragment(private val isShouldSearchNavController: Boolean = false) : Fragment(), CoroutineScope, BackPressHandler {
    private val job = SupervisorJob()
    protected var waiterView: View? = null
    var navController: NavController? = null

    override val coroutineContext = Dispatchers.Default + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (optionsMenu() != null) {
            setHasOptionsMenu(true)
        }
        pullArguments(arguments)
        initialize()
    }

    open fun optionsMenu(): Int? = null
    open fun pullArguments(arguments: Bundle?) {}
    open fun initialize() {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bindingItems = bindingItems()

        return if (bindingItems != null) {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layout(), container, false)
            bindingItems.forEach { binding.setVariable(it.key, it.value) }
            binding.setLifecycleOwner(viewLifecycleOwner)
            binding.root
        } else {
            inflater.inflate(layout(), container, false)
        }
    }

    abstract fun layout(): Int
    open fun bindingItems(): Map<Int, Any>? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val toolbarTitle = toolbarTitle()
        if (toolbarTitle != null) {
            (activity as? AppCompatActivity)?.supportActionBar?.title = getString(toolbarTitle)
        }
        val waiterId = waiter()
        if (waiterId != null) {
            waiterView = activity?.findViewById(waiterId)
        }
        if (isShouldSearchNavController && navController == null) {
            view?.let { navController = Navigation.findNavController(it) }
        }
        initializeViews()
        observeLiveData(viewLifecycleOwner)
        if (savedInstanceState == null) {
            onInitialStart()
        } else {
            restoreState(savedInstanceState)
        }
        setListeners()
        onStandby()
    }

    open fun toolbarTitle(): Int? = null
    open fun waiter(): Int? = null
    open fun initializeViews() {}
    open fun observeLiveData(owner: LifecycleOwner) {}
    open fun onInitialStart() {}
    open fun restoreState(savedInstanceState: Bundle) {}
    open fun setListeners() {}
    open fun onStandby() {}

    override fun onBackPressed() = false

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val menuResId = optionsMenu()
        if (menuResId != null) {
            inflater.inflate(menuResId, menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        } else {
            onMenuItemSelected(item.itemId) ?: super.onOptionsItemSelected(item)
        }

    open fun onMenuItemSelected(id: Int): Boolean? = null

    protected fun showWaiter(show: Boolean) {
        checkNotNull(waiterView)
        if (show) {
            postDelayed(300) { waiterView?.visibleOrInvisible = true }
        } else {
            waiterView?.goneOrVisible = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }
}