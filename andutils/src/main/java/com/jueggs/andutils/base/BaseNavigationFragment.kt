package com.jueggs.andutils.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.jueggs.andutils.Util.doWithDelay
import com.jueggs.andutils.adapter.OnBackPressedCallbackAdapter
import com.jueggs.andutils.extension.makeGone
import com.jueggs.andutils.extension.makeVisible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

abstract class BaseNavigationFragment() : Fragment(), CoroutineScope {
    private val job = SupervisorJob()
    protected var waiterView: View? = null
    protected lateinit var navController: NavController
    override val coroutineContext = Dispatchers.Default + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        optionsMenu()?.let { setHasOptionsMenu(true) }
        pullArguments(arguments)
        initialize()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bindingItems = bindingItems()

        return if (bindingItems != null) {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layout(), container, false)
            bindingItems.forEach { binding.setVariable(it.key, it.value) }
            binding.lifecycleOwner = viewLifecycleOwner
            binding.root
        } else {
            inflater.inflate(layout(), container, false)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, OnBackPressedCallbackAdapter(this::onBackPressed))
        waiter()?.let { waiterView = requireActivity().findViewById(it) }
        navController = Navigation.findNavController(requireView())

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        optionsMenu()?.let { inflater.inflate(it, menu) }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = onMenuItemSelected(item.itemId) ?: super.onOptionsItemSelected(item)

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }

    abstract fun layout(): Int

    open fun optionsMenu(): Int? = null
    open fun pullArguments(arguments: Bundle?) {}
    open fun initialize() {}
    open fun initializeViews() {}
    open fun bindingItems(): Map<Int, Any>? = null
    open fun waiter(): Int? = null
    open fun setListeners() {}
    open fun observeLiveData(owner: LifecycleOwner) {}
    open fun onInitialStart() {}
    open fun restoreState(savedInstanceState: Bundle) {}
    open fun onMenuItemSelected(id: Int): Boolean? = null
    open fun onStandby() {}
    open fun onBackPressed() {}

    protected fun showWaiter(show: Boolean) {
        checkNotNull(waiterView)
        if (show) {
            doWithDelay(300) { waiterView?.makeVisible() }
        } else {
            waiterView?.makeGone()
        }
    }
}