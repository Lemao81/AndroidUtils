package com.jueggs.andutils.extension

import android.transition.TransitionInflater
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import androidx.annotation.TransitionRes
import com.jueggs.andutils.adapter.StandardFragmentPagerAdapter
import com.jueggs.andutils.helper.DatePicker
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.jueggs.jutils.Util.areAllNull
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton
import org.joda.time.LocalDate

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
    if (areAllNull(enterResId, exitResId, reenterResId, returnResId)) return

    val transitionInflater = TransitionInflater.from(this.context)
    enterResId?.let { enterTransition = transitionInflater.inflateTransition(it) }
    exitResId?.let { exitTransition = transitionInflater.inflateTransition(it) }
    reenterResId?.let { reenterTransition = transitionInflater.inflateTransition(it) }
    returnResId?.let { returnTransition = transitionInflater.inflateTransition(it) }
}

fun Fragment.withArguments(vararg arguments: Pair<String, Any>): Fragment = apply { setArguments(bundleOf(*arguments)) }

fun Fragment.datePicker(date: LocalDate = LocalDate.now(), action: (LocalDate) -> Unit) = DatePicker(date, action).show(childFragmentManager)

fun Fragment.setupTabPager(viewPager: ViewPager, tabLayout: TabLayout, pageTitleArrayResId: Int, vararg fragmentFactoryList: () -> Fragment) {
    val adapter = StandardFragmentPagerAdapter(fragmentFactoryList.toList(), pageTitleArrayResId, context, childFragmentManager)
    viewPager.adapter = adapter
    tabLayout.setupWithViewPager(viewPager)
}

fun Fragment.hideKeyboard() = activity?.hideKeyboard()