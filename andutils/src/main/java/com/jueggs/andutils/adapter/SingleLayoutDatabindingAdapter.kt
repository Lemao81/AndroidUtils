package com.jueggs.andutils.adapter

import android.support.v7.util.DiffUtil
import com.jueggs.andutils.helper.DiffUtilCallback
import kotlin.reflect.*

abstract class SingleLayoutDatabindingAdapter<TItems : Any, in TIdProperty>(
        private val layoutId: Int,
        private val items: MutableList<TItems> = arrayListOf(),
        layoutIncluded: Boolean = false) : BaseDatabindingAdapter(layoutIncluded) {

    override fun getLayoutIdForPosition(position: Int): Int = layoutId

    override fun getItemForPosition(position: Int): Any = items[position]

    override fun getItemAmount(): Int = items.size

    fun setItems(newItems: List<TItems>, idProperty: KProperty1<TItems, TIdProperty>, getChangePayload: ((Int, Int) -> Any?)? = null) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(items, newItems, idProperty, getChangePayload))
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }
}