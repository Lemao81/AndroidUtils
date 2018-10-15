package com.jueggs.andutils.extension

import android.support.annotation.*
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.transition.TransitionInflater
import com.jueggs.andutils.adapter.StandardFragmentPagerAdapter
import com.jueggs.andutils.helper.DatePicker
import org.jetbrains.anko.*
import java.util.*

fun Fragment.showConfirmDialog(title: CharSequence?, message: CharSequence, confirmAction: (Unit) -> Unit, denyAction: (Unit) -> Unit = {}) =
        context?.alert(message, title) {
            yesButton { confirmAction(Unit) }
            noButton { denyAction(Unit) }
        }?.show()

fun Fragment.showConfirmDialog(@StringRes titleResId: Int?, @StringRes messageResId: Int, confirmAction: (Unit) -> Unit, denyAction: (Unit) -> Unit = {}) =
        showConfirmDialog(if (titleResId != null) getString(titleResId) else null, getString(messageResId), confirmAction, denyAction)

fun Fragment.showSelection(title: CharSequence?, items: List<CharSequence>, onSelectIndex: (Int) -> Unit = {}, onSelectString: (String) -> Unit = {}) =
        context?.showSelection(title, items, onSelectIndex, onSelectString)

fun Fragment.showSelection(@StringRes titleResId: Int?, items: List<CharSequence>, onSelectIndex: (Int) -> Unit = {}, onSelectString: (String) -> Unit = {}) =
        context?.showSelection(titleResId, items, onSelectIndex, onSelectString)

fun Fragment.showSelection(@StringRes titleResId: Int?, @ArrayRes arrayResId: Int, onSelectIndex: (Int) -> Unit = {}, onSelectString: (String) -> Unit = {}) =
        context?.showSelection(titleResId, arrayResId, onSelectIndex, onSelectString)

fun Fragment.setNavigationTransitions(@TransitionRes enterResId: Int?, @TransitionRes exitResId: Int?, @TransitionRes reenterResId: Int?, @TransitionRes returnResId: Int?) {
    val transitionInflater = TransitionInflater.from(this.context)
    if (enterResId != null)
        enterTransition = transitionInflater.inflateTransition(enterResId)
    if (exitResId != null)
        exitTransition = transitionInflater.inflateTransition(exitResId)
    if (reenterResId != null)
        reenterTransition = transitionInflater.inflateTransition(reenterResId)
    if (returnResId != null)
        returnTransition = transitionInflater.inflateTransition(returnResId)
}

fun Fragment.withArguments(vararg arguments: Pair<String, Any>): Fragment = apply { setArguments(bundleOf(*arguments)) }

fun Fragment.datePicker(date: Date = Date(), action: (Date) -> Unit) = DatePicker(date, action).show(childFragmentManager)

fun Fragment.setupTabPager(viewPager: ViewPager, tabLayout: TabLayout, pageTitleArrayResId: Int, vararg fragmentFactoryList: () -> Fragment) {
    val adapter = StandardFragmentPagerAdapter(fragmentFactoryList.toList(), pageTitleArrayResId, context, childFragmentManager)
    viewPager.adapter = adapter
    tabLayout.setupWithViewPager(viewPager)
}