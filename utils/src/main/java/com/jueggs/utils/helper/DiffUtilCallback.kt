package com.jueggs.utils.helper

import android.support.v7.util.DiffUtil
import kotlin.reflect.*

class DiffUtilCallback<T>(private var oldList: List<T>, private var newList: List<T>, private val idProperty: KProperty1<T, *>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = idProperty.get(oldList[oldItemPosition]) == idProperty.get(newList[newItemPosition])

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition]?.equals(newList[newItemPosition]) ?: false
}