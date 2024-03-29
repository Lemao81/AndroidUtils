package com.jueggs.firebaseutils.util

import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.TaskCompletionSource
import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.DatabaseReference
import com.jueggs.andutils.extension.then
import com.jueggs.firebaseutils.Constant.DBTYPE_LIST
import com.jueggs.firebaseutils.Constant.DBTYPE_MODEL
import com.jueggs.firebaseutils.extension.toModelListSingle
import com.jueggs.firebaseutils.extension.toModelSingle

//region find multiple
inline fun <reified T : Any, reified U : Any> findMultiple(ref1: Pair<DatabaseReference, Int>, ref2: Pair<DatabaseReference, Int>, crossinline action: (Any?, Any?) -> Unit) {
    val tasks = arrayListOf<Task<*>>()
    addTask<T>(ref1, tasks)
    addTask<U>(ref2, tasks)
    Tasks.whenAll(tasks).then {
        val results = tasks.map { if (it.isSuccessful) it.result else null }
        action(results[0], results[1])
    }
}

inline fun <reified T : Any, reified U : Any, reified V : Any> findMultiple(
    ref1: Pair<DatabaseReference, Int>,
    ref2: Pair<DatabaseReference, Int>,
    ref3: Pair<DatabaseReference, Int>,
    crossinline action: (Any?, Any?, Any?) -> Unit
) {
    val tasks = arrayListOf<Task<*>>()
    addTask<T>(ref1, tasks)
    addTask<U>(ref2, tasks)
    addTask<V>(ref3, tasks)
    Tasks.whenAll(tasks).then {
        val results = tasks.map { if (it.isSuccessful) it.result else null }
        action(results[0], results[1], results[2])
    }
}

inline fun <reified T : Any, reified U : Any, reified V : Any, reified W : Any> findMultiple(
    ref1: Pair<DatabaseReference, Int>,
    ref2: Pair<DatabaseReference, Int>,
    ref3: Pair<DatabaseReference, Int>,
    ref4: Pair<DatabaseReference, Int>,
    crossinline action: (Any?, Any?, Any?, Any?) -> Unit
) {
    val tasks = arrayListOf<Task<*>>()
    addTask<T>(ref1, tasks)
    addTask<U>(ref2, tasks)
    addTask<V>(ref3, tasks)
    addTask<W>(ref4, tasks)
    Tasks.whenAll(tasks).then {
        val results = tasks.map { if (it.isSuccessful) it.result else null }
        action(results[0], results[1], results[2], results[3])
    }
}

inline fun <reified T : Any, reified U : Any, reified V : Any, reified W : Any, reified X : Any> findMultiple(
    ref1: Pair<DatabaseReference, Int>,
    ref2: Pair<DatabaseReference, Int>,
    ref3: Pair<DatabaseReference, Int>,
    ref4: Pair<DatabaseReference, Int>,
    ref5: Pair<DatabaseReference, Int>,
    crossinline action: (Any?, Any?, Any?, Any?, Any?) -> Unit
) {
    val tasks = arrayListOf<Task<*>>()
    addTask<T>(ref1, tasks)
    addTask<U>(ref2, tasks)
    addTask<V>(ref3, tasks)
    addTask<W>(ref4, tasks)
    addTask<X>(ref5, tasks)
    Tasks.whenAll(tasks).then {
        val results = tasks.map { if (it.isSuccessful) it.result else null }
        action(results[0], results[1], results[2], results[3], results[4])
    }
}

inline fun <reified T : Any, reified U : Any, reified V : Any, reified W : Any, reified X : Any, reified Y : Any> findMultiple(
    ref1: Pair<DatabaseReference, Int>,
    ref2: Pair<DatabaseReference, Int>,
    ref3: Pair<DatabaseReference, Int>,
    ref4: Pair<DatabaseReference, Int>,
    ref5: Pair<DatabaseReference, Int>,
    ref6: Pair<DatabaseReference, Int>,
    crossinline action: (Any?, Any?, Any?, Any?, Any?, Any?) -> Unit
) {
    val tasks = arrayListOf<Task<*>>()
    addTask<T>(ref1, tasks)
    addTask<U>(ref2, tasks)
    addTask<V>(ref3, tasks)
    addTask<W>(ref4, tasks)
    addTask<X>(ref5, tasks)
    addTask<Y>(ref6, tasks)
    Tasks.whenAll(tasks).then {
        val results = tasks.map { if (it.isSuccessful) it.result else null }
        action(results[0], results[1], results[2], results[3], results[4], results[5])
    }
}

inline fun <reified T : Any, reified U : Any, reified V : Any, reified W : Any, reified X : Any, reified Y : Any, reified Z : Any> findMultiple(
    ref1: Pair<DatabaseReference, Int>,
    ref2: Pair<DatabaseReference, Int>,
    ref3: Pair<DatabaseReference, Int>,
    ref4: Pair<DatabaseReference, Int>,
    ref5: Pair<DatabaseReference, Int>,
    ref6: Pair<DatabaseReference, Int>,
    ref7: Pair<DatabaseReference, Int>,
    crossinline action: (Any?, Any?, Any?, Any?, Any?, Any?, Any?) -> Unit
) {
    val tasks = arrayListOf<Task<*>>()
    addTask<T>(ref1, tasks)
    addTask<U>(ref2, tasks)
    addTask<V>(ref3, tasks)
    addTask<W>(ref4, tasks)
    addTask<X>(ref5, tasks)
    addTask<Y>(ref6, tasks)
    addTask<Z>(ref7, tasks)
    Tasks.whenAll(tasks).then {
        val results = tasks.map { if (it.isSuccessful) it.result else null }
        action(results[0], results[1], results[2], results[3], results[4], results[5], results[6])
    }
}

inline fun <reified T : Any> addTask(ref: Pair<DatabaseReference, Int>, tasks: ArrayList<Task<*>>) {
    val (reference, type) = ref
    when (type) {
        DBTYPE_MODEL -> {
            val taskSource = TaskCompletionSource<T>()
            reference.toModelSingle<T>().subscribe({ taskSource.setResult(it) }, { taskSource.setException(Exception(it)) })
            tasks.add(taskSource.task)
        }
        DBTYPE_LIST -> {
            val taskSource = TaskCompletionSource<List<T>>()
            reference.toModelListSingle<T>().subscribe({ taskSource.setResult(it) }, { taskSource.setException(Exception(it)) })
            tasks.add(taskSource.task)
        }
    }
}
//endregion