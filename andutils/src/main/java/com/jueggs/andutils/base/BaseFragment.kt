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
    protected var navController: NavController? = null

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

        toolbarTitle()?.let {
            val supportActionBar = (activity as? AppCompatActivity)?.supportActionBar
                ?: throw UninitializedPropertyAccessException("derive activity from ${BaseActivity::class.qualifiedName} " +
                    "and initialize toolbar with ${BaseActivity::toolbar.name}()")
            supportActionBar.title = getString(it)
        }
        waiter()?.let { waiterView = activity?.findViewById(it) }
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

    override fun onBackPressed() = false

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        optionsMenu()?.let { inflater.inflate(it, menu) }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        } else {
            onMenuItemSelected(item.itemId) ?: super.onOptionsItemSelected(item)
        }

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
    open fun toolbarTitle(): Int? = null
    open fun waiter(): Int? = null
    open fun setListeners() {}
    open fun observeLiveData(owner: LifecycleOwner) {}
    open fun onInitialStart() {}
    open fun restoreState(savedInstanceState: Bundle) {}
    open fun onMenuItemSelected(id: Int): Boolean? = null
    open fun onStandby() {}

    protected fun showWaiter(show: Boolean) {
        checkNotNull(waiterView)
        if (show) {
            postDelayed(300) { waiterView?.visibleOrInvisible = true }
        } else {
            waiterView?.goneOrVisible = true
        }
    }
}