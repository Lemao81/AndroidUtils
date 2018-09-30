package com.jueggs.andutils.extension

import com.jueggs.andutils.observable.*
import com.jueggs.andutils.util.logDebug
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.scheduleIoMain(): Observable<T> = subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.logError(): Observable<T> = doOnError { logDebug(it.message) }

fun <T> Observable<T>.check(predicate: (T) -> Boolean): Observable<T> = CheckObservable(this, predicate)

fun <T> Single<T>.check(predicate: (T) -> Boolean): Single<T> = CheckSingle(this, predicate)

fun <T> Completable.continueWith(next: T): Single<T> = Single.create { emitter ->
    subscribe({ emitter.onSuccess(next) }, emitter::onError)
}