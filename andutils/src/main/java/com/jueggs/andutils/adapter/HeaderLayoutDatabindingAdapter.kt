package com.jueggs.andutils.adapter

import com.jueggs.andutils.extension.setAdapterListItems

abstract class HeaderLayoutDatabindingAdapter<in THeader : Any, in TList : Any>(
        private val headerLayoutId: Int,
        private val listItemLayoutId: Int,
        private var header: THeader? = null,
        private val listItems: MutableList<TList> = arrayListOf(),
        layoutIncluded: Boolean = false) : BaseDatabindingAdapter(layoutIncluded) {

    override fun getLayoutIdForPosition(position: Int) = if (position == 0) headerLayoutId else listItemLayoutId

    override fun getItemForPosition(position: Int) = if (position == 0) header ?: Any() else listItems[position - 1]

    override fun getItemAmount() = listItems.size + 1

    fun setHeader(newHeader: THeader, notifyDataChange: Boolean = true) {
        header = newHeader
        if (notifyDataChange) notifyDataSetChanged()
    }

    fun setItems(newItems: List<TList>) = setAdapterListItems(newItems, listItems)

    fun setHeaderAndItems(newHeader: THeader, newItems: List<TList>) {
        setHeader(newHeader, false)
        setItems(newItems)
    }
}