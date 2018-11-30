package com.jueggs.andutils.usecase

import com.jueggs.andutils.result.ValidationResult

interface Validator<TData> {
    suspend operator fun invoke(data: TData): ValidationResult
}