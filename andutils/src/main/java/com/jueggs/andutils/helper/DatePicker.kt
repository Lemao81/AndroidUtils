package com.jueggs.andutils.helper

import android.annotation.SuppressLint
import android.app.*
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.DatePicker
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("ValidFragment")
class DatePicker() : DialogFragment(), DatePickerDialog.OnDateSetListener {

    constructor(date: Date) : this() {
        this.date = date
    }

    constructor(year: Int, month: Int, dayOfMonth: Int) : this() {
        this.date = Calendar.getInstance().also { it.set(year, month, dayOfMonth) }.time
    }

    constructor(calendar: Calendar) : this() {
        this.date = calendar.time
    }

    constructor(time: Long) : this() {
        this.date = Date(time)
    }

    var format: String = "MM/dd/yy"
        set(value) {
            field = value
            dateFormat = SimpleDateFormat(value, locale)
        }
    var locale: Locale = Locale.US
        set(value) {
            field = value
            dateFormat = SimpleDateFormat(format, value)
        }
    private var dateFormat: SimpleDateFormat = SimpleDateFormat(format, locale)

    var date: Date = Date()
    var time: Long = 0
        get() = date.time
    var text: String = ""
        get() = dateFormat.format(date)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance().also { it.time = date }
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(activity, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        date = Calendar.getInstance().also { it.set(year, month, dayOfMonth) }.time
    }
}