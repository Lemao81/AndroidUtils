package com.jueggs.andutils.observable

import io.reactivex.*

class CheckSingle<T>(private val source: Single<T>, private val predicate: (T) -> Boolean) : Single<T>() {
    override fun subscribeActual(observer: SingleObserver<in T>) {
        source.subscribe(CheckSingleObserver<T>(observer, predicate))
    }
}