package com.jueggs.andutils.extension

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import com.jueggs.andutils.helper.DatePicker
import com.jueggs.andutils.isLollipopOrAboveUtil
import org.joda.time.LocalDate

fun FragmentActivity.finishAfterTransitionCompat() {
    if (isLollipopOrAboveUtil()) finishAfterTransition()
    else supportFinishAfterTransition()
}

fun FragmentActivity.postponeEnterTransitionCompat() {
    if (isLollipopOrAboveUtil()) postponeEnterTransition()
    else supportPostponeEnterTransition()
}

fun FragmentActivity.startPostponedEnterTransitionCompat() {
    if (isLollipopOrAboveUtil()) startPostponedEnterTransition()
    else supportStartPostponedEnterTransition()
}

fun FragmentActivity.datePicker(date: LocalDate = LocalDate.now(), onDateSet: (LocalDate) -> Unit, onClose: (() -> Unit)? = null) = DatePicker(date, onDateSet, onClose).show(supportFragmentManager)

fun FragmentActivity.isResultOk(resultCode: Int): Boolean = resultCode == Activity.RESULT_OK

fun FragmentActivity.isResultNotOk(resultCode: Int): Boolean = resultCode == Activity.RESULT_CANCELED