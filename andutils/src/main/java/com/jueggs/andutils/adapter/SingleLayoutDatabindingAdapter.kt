package com.jueggs.andutils.adapter

import androidx.recyclerview.widget.DiffUtil
import com.jueggs.andutils.callback.DiffUtilCallback
import kotlin.reflect.KProperty1

abstract class SingleLayoutDatabindingAdapter<TItems : Any, in TIdProperty>(
    private val layoutId: Int,
    private val items: MutableList<TItems> = arrayListOf(),
    layoutIncluded: Boolean = false
) : BaseDatabindingAdapter(layoutIncluded) {

    override fun getLayoutIdForPosition(position: Int): Int = layoutId

    override fun getItemForPosition(position: Int): Any = items[position]

    override fun getItemAmount(): Int = items.size

    fun setItems(newItems: List<TItems>, idProperty: KProperty1<TItems, TIdProperty>, getChangePayload: ((Int, Int) -> Any?)? = null) {
        checkNotNull(newItems)
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(items, newItems, idProperty, getChangePayload))
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }
}