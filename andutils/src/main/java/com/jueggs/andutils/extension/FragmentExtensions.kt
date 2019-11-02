package com.jueggs.andutils.extension

import android.transition.TransitionInflater
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import androidx.annotation.TransitionRes
import com.jueggs.andutils.helper.DatePicker
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.jueggs.andutils.adapter.StandardFragmentPagerAdapter
import com.jueggs.jutils.Util.areAllNull
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton
import org.joda.time.LocalDate

fun Fragment.showConfirmDialog(title: CharSequence?, message: CharSequence, confirmAction: () -> Unit, denyAction: () -> Unit = {}) =
    requireContext().alert(message, title) {
        yesButton { confirmAction() }
        noButton { denyAction() }
    }.show()

fun Fragment.showConfirmDialog(@StringRes titleResId: Int?, @StringRes messageResId: Int, confirmAction: () -> Unit, denyAction: () -> Unit = {}) =
    showConfirmDialog(if (titleResId != null) getString(titleResId) else null, getString(messageResId), confirmAction, denyAction)

fun Fragment.showSelection(title: CharSequence?, items: List<CharSequence>, onSelectIndex: (Int) -> Unit = {}, onSelectString: (String) -> Unit = {}) =
    requireContext().showSelection(title, items, onSelectIndex, onSelectString)

fun Fragment.showSelection(@StringRes titleResId: Int?, items: List<CharSequence>, onSelectIndex: (Int) -> Unit = {}, onSelectString: (String) -> Unit = {}) =
    requireContext().showSelection(titleResId, items, onSelectIndex, onSelectString)

fun Fragment.showSelection(@StringRes titleResId: Int?, @ArrayRes arrayResId: Int, onSelectIndex: (Int) -> Unit = {}, onSelectString: (String) -> Unit = {}) =
    requireContext().showSelection(titleResId, arrayResId, onSelectIndex, onSelectString)

fun Fragment.setNavigationTransitions(@TransitionRes enterResId: Int?, @TransitionRes exitResId: Int?, @TransitionRes reenterResId: Int?, @TransitionRes returnResId: Int?) {
    if (areAllNull(enterResId, exitResId, reenterResId, returnResId)) return

    val transitionInflater = TransitionInflater.from(this.context)
    enterResId?.let { enterTransition = transitionInflater.inflateTransition(it) }
    exitResId?.let { exitTransition = transitionInflater.inflateTransition(it) }
    reenterResId?.let { reenterTransition = transitionInflater.inflateTransition(it) }
    returnResId?.let { returnTransition = transitionInflater.inflateTransition(it) }
}

fun Fragment.withArguments(vararg arguments: Pair<String, Any>): Fragment = apply { setArguments(bundleOf(*arguments)) }

fun Fragment.datePicker(date: LocalDate = LocalDate.now(), onDateSet: (LocalDate) -> Unit, onClose: (() -> Unit)? = null) = DatePicker(date, onDateSet, onClose).show(childFragmentManager)

fun Fragment.setupTabPager(viewPager: ViewPager, tabLayout: TabLayout, pageTitleArrayResId: Int, vararg fragments: Fragment) {
    requireContext().let {
        val adapter = StandardFragmentPagerAdapter(childFragmentManager, fragments.toList(), it.getStringArray(pageTitleArrayResId))
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}

fun Fragment.hideKeyboard() {
    requireActivity().hideKeyboard()
}

fun Fragment.showKeyboard() {
    requireActivity().showKeyboard()
}