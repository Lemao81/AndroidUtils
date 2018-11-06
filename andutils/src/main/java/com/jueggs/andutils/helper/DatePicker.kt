package com.jueggs.andutils.helper

import android.annotation.SuppressLint
import android.app.*
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.widget.DatePicker
import com.jueggs.jutils.extension.*
import org.joda.time.LocalDate
import java.util.*

@SuppressLint("ValidFragment")
class DatePicker(private val date: Date = Date(), private val action: (Date) -> Unit) : DialogFragment(), DatePickerDialog.OnDateSetListener {
    constructor(year: Int, month: Int, dayOfMonth: Int, action: (Date) -> Unit) : this(Calendar.getInstance().also { it.set(year, month, dayOfMonth) }.time, action)

    constructor(calendar: Calendar, action: (Date) -> Unit) : this(calendar.time, action)

    constructor(time: Long, action: (Date) -> Unit) : this(Date(time), action)

    constructor(localDate: LocalDate, action: (Date) -> Unit) : this(localDate.toDate(), action)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance().also { it.time = date }
        return DatePickerDialog(checkNotNull(activity), this, calendar.year, calendar.month, calendar.dayOfMonth)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        action(Calendar.getInstance().also { it.set(year, month, dayOfMonth) }.time)
        dismiss()
    }

    fun show(manager: FragmentManager) = show(manager, TAG)

    companion object {
        val TAG: String = DatePicker::class.java.name
    }
}