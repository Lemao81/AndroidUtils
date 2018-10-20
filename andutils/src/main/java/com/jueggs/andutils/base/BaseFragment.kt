package com.jueggs.andutils.base

import android.databinding.*
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.*
import androidx.navigation.NavController
import com.jueggs.andutils.R
import com.jueggs.andutils.extension.setNavigationTransitions

abstract class BaseFragment : Fragment(), BackPressHandler {
    protected var navController: NavController? = null
    protected var waiter: ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (optionsMenu() != null) setHasOptionsMenu(true)
        setNavigationTransitions(enterTransition(), exitTransition(), reenterTransition(), returnTransition())

        pullArguments(arguments)
        initialize()
        setObservers()
    }

    open fun pullArguments(arguments: Bundle?) {}
    open fun initialize() {}
    open fun setObservers() {}

    open fun enterTransition(): Int? = R.transition.fade
    open fun exitTransition(): Int? = R.transition.fade
    open fun reenterTransition(): Int? = R.transition.fade
    open fun returnTransition(): Int? = R.transition.fade

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bindingItems = bindingItems()

        return if (bindingItems != null) {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layout(), container, false)
            bindingItems.forEach { binding.setVariable(it.key, it.value) }
            binding.setLifecycleOwner(this)
            binding.root
        } else
            inflater.inflate(layout(), container, false)
    }

    abstract fun layout(): Int
    open fun bindingItems(): Map<Int, Any>? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val toolbarTitle = toolbarTitle()
        if (toolbarTitle != null)
            (activity as? AppCompatActivity)?.supportActionBar?.title = getString(toolbarTitle)

        initializeViews()

        if (savedInstanceState == null)
            onInitialStart()
        else
            restoreState(savedInstanceState)

        setListeners()
    }

    open fun toolbarTitle(): Int? = null
    open fun onInitialStart() {}
    open fun restoreState(savedInstanceState: Bundle) {}

    open fun initializeViews() {}
    open fun setListeners() {}

    override fun onBackPressed() = false

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        val resId = optionsMenu()
        if (resId != null)
            inflater?.inflate(resId, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    open fun optionsMenu(): Int? = null

    override fun onOptionsItemSelected(item: MenuItem) = if (item.itemId == android.R.id.home) onBackPressed()
    else onMenuItemSelected(item.itemId) ?: super.onOptionsItemSelected(item)

    open fun onMenuItemSelected(id: Int): Boolean? = null

    protected fun addFragment(@IdRes containerViewId: Int, fragment: Fragment) = childFragmentManager.beginTransaction().add(containerViewId, fragment).addToBackStack(fragment::class.simpleName).commit()
    protected fun replaceFragment(@IdRes containerViewId: Int, fragment: Fragment) = childFragmentManager.beginTransaction().replace(containerViewId, fragment).commit()
    fun popFragment() = childFragmentManager.popBackStack()
}