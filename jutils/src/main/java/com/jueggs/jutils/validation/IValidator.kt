package com.jueggs.jutils.validation

interface IValidator<TData, TResult> {
    suspend operator fun invoke(data: TData): TResult
}