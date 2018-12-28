package com.jueggs.andutils.helper

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import org.joda.time.LocalDate

@SuppressLint("ValidFragment")
class DatePicker(private val date: LocalDate = LocalDate.now(), private val onDateSet: (LocalDate) -> Unit, private val onClose: (() -> Unit)? = null)
    : DialogFragment(), DatePickerDialog.OnDateSetListener {

    constructor(year: Int, month: Int, dayOfMonth: Int, onDateSet: (LocalDate) -> Unit, onClose: (() -> Unit)? = null) : this(LocalDate(year, month, dayOfMonth), onDateSet, onClose)

    override fun onCreateDialog(savedInstanceState: Bundle?) = DatePickerDialog(checkNotNull(activity), this, date.year, date.monthOfYear - 1, date.dayOfMonth)

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        onDateSet(LocalDate(year, month + 1, dayOfMonth))
        dismiss()
    }

    override fun onDestroy() {
        onClose?.invoke()
        super.onDestroy()
    }

    fun show(manager: FragmentManager) = show(manager, TAG)

    companion object {
        val TAG: String = DatePicker::class.java.name
    }
}