package com.jueggs.andutils.usecase

import com.jueggs.andutils.result.ValidationResult

interface Validator<TData> {
    suspend fun validate(data: TData): ValidationResult
}