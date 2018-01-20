package com.jueggs.utils.adapter

import com.jueggs.utils.extension.setAdapterListItems

abstract class SingleLayoutDatabindingAdapter<in T : Any>(
        private val layoutId: Int,
        private val items: MutableList<T> = arrayListOf(),
        layoutIncluded: Boolean = false) : BaseDatabindingAdapter(layoutIncluded) {

    override fun getLayoutIdForPosition(position: Int): Int = layoutId

    override fun getItemForPosition(position: Int): Any = items[position]

    override fun getItemAmount(): Int = items.size

    fun setItems(newItems: List<T>) = setAdapterListItems(newItems, items)
}