package com.jueggs.andutils.extension

import com.jueggs.andutils.d
import com.jueggs.andutils.observable.CheckObservable
import com.jueggs.andutils.observable.CheckSingle
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.scheduleIoMain(): Observable<T> = subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.scheduleCompMain(): Observable<T> = subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.observeOnMain(): Observable<T> = observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.logError(): Observable<T> = doOnError(::d)

fun <T> Observable<T>.check(predicate: (T) -> Boolean): Observable<T> = CheckObservable(this, predicate)

fun <T> Single<T>.check(predicate: (T) -> Boolean): Single<T> = CheckSingle(this, predicate)

fun <T> Completable.continueWith(next: T): Single<T> = Single.create { emitter ->
    subscribe({ emitter.onSuccess(next) }, emitter::onError)
}