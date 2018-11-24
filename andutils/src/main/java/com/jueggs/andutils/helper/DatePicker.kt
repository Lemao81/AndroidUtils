package com.jueggs.andutils.helper

import android.annotation.SuppressLint
import android.app.*
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.widget.DatePicker
import org.joda.time.LocalDate

@SuppressLint("ValidFragment")
class DatePicker(private val date: LocalDate = LocalDate.now(), private val action: (LocalDate) -> Unit) : DialogFragment(), DatePickerDialog.OnDateSetListener {
    constructor(year: Int, month: Int, dayOfMonth: Int, action: (LocalDate) -> Unit) : this(LocalDate(year, month, dayOfMonth), action)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return DatePickerDialog(checkNotNull(activity), this, date.year, date.monthOfYear, date.dayOfMonth)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        action(LocalDate(year, month + 1, dayOfMonth))
        dismiss()
    }

    fun show(manager: FragmentManager) = show(manager, TAG)

    companion object {
        val TAG: String = DatePicker::class.java.name
    }
}