package com.jueggs.andutils.result

import android.support.annotation.StringRes

sealed class ValidationResult

object Valid : ValidationResult()
data class Invalid(@StringRes val resId: Int) : ValidationResult()