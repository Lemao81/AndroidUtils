package com.jueggs.rxutils

import io.reactivex.Observable
import java.lang.IllegalStateException
import java.security.InvalidParameterException

object Util {
    fun <T> mergeObservables(vararg observables: Observable<T>): Observable<T> {
        return if (observables.size > 4) {
            val merge = Observable.merge(observables[0], observables[1], observables[2], observables[3])
            val reduced = observables.slice(IntRange(4, observables.size - 1)).toMutableList()
            reduced.add(merge)
            mergeObservables(*reduced.toTypedArray())
        } else when (observables.size) {
            0 -> throw InvalidParameterException("No observables to merge")
            1 -> observables[0]
            2 -> Observable.merge(observables[0], observables[1])
            3 -> Observable.merge(observables[0], observables[1], observables[2])
            4 -> Observable.merge(observables[0], observables[1], observables[2], observables[3])
            else -> throw IllegalStateException()
        }
    }
}