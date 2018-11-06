package com.jueggs.andutils.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.jueggs.andutils.*
import com.jueggs.andutils.extension.*
import com.jueggs.jutils.extension.*
import java.lang.reflect.Modifier

abstract class BaseDatabindingAdapter(private var layoutIncluded: Boolean = false) : RecyclerView.Adapter<BaseDatabindingAdapter.ViewHolder>() {
    var eventHandler: Any? = null

    override fun onCreateViewHolder(parent: ViewGroup, layoutId: Int): BaseDatabindingAdapter.ViewHolder = BaseDatabindingAdapter.ViewHolder(
            DataBindingUtil.inflate(parent.layoutInflater(), layoutId, parent, false), getBindingVariableId(), eventHandler, getEventhandlerVariableId(), layoutIncluded)

    override fun onBindViewHolder(holder: BaseDatabindingAdapter.ViewHolder, position: Int) = holder.bind(getItemForPosition(position))

    override fun getItemViewType(position: Int): Int = getLayoutIdForPosition(position)

    override fun getItemCount(): Int = getItemAmount()

    abstract fun getLayoutIdForPosition(position: Int): Int

    abstract fun getItemForPosition(position: Int): Any

    abstract fun getItemAmount(): Int

    abstract fun getBindingVariableId(): Int

    open fun getEventhandlerVariableId(): Int? = null

    class ViewHolder(
            private val binding: ViewDataBinding,
            private val bindingVariableId: Int,
            private val eventHandler: Any?,
            private val eventHandlerVariableId: Int?,
            private val layoutIncluded: Boolean) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Any) {
            binding.setVariable(bindingVariableId, item)
            if (layoutIncluded)
                bindIncludedLayout(item)

            if (eventHandler != null) {
                val id = checkNotNull(eventHandlerVariableId) { ERROR_NO_EVENTHANDLER_ID.format(BaseDatabindingAdapter::getEventhandlerVariableId.name) }
                binding.setVariable(id, eventHandler)
            }
            binding.executePendingBindings()
        }

        private fun bindIncludedLayout(item: Any) {
            val layoutField = binding.findField(INCLUDED_LAYOUT_ID_IDENTIFIER, Modifier.PUBLIC)
            val field = checkNotNull(layoutField) { ERROR_NO_INCLUDED_LAYOUT.format(BaseDatabindingAdapter::layoutIncluded.name, INCLUDED_LAYOUT_ID_IDENTIFIER) }

            val includeBinding = field.get(binding)
            val setVariableMethod = includeBinding.findMethod(ViewDataBinding::setVariable.name, Modifier.PUBLIC)
            val method = checkNotNull(setVariableMethod) { ERROR_NO_SETVARIABLE_METHOD.format(ViewDataBinding::setVariable.name) }

            method.invoke(includeBinding, bindingVariableId, item)
        }
    }

    companion object {
        const val INCLUDED_LAYOUT_ID_IDENTIFIER = "include"
    }
}