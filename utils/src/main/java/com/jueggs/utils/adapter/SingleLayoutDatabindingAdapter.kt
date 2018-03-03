package com.jueggs.utils.adapter

import android.support.v7.util.DiffUtil
import com.jueggs.utils.helper.DiffUtilCallback
import kotlin.reflect.*

abstract class SingleLayoutDatabindingAdapter<T : Any, in R>(
        private val layoutId: Int,
        private val items: MutableList<T> = arrayListOf(),
        layoutIncluded: Boolean = false) : BaseDatabindingAdapter(layoutIncluded) {

    override fun getLayoutIdForPosition(position: Int): Int = layoutId

    override fun getItemForPosition(position: Int): Any = items[position]

    override fun getItemAmount(): Int = items.size

    fun setItems(newItems: List<T>, idProperty: KProperty1<T, R>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(items, newItems, idProperty))
        diffResult.dispatchUpdatesTo(this)
    }
}