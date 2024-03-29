package com.jueggs.andutils.adapter

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.jueggs.andutils.extension.layoutInflater
import com.jueggs.jutils.extension.findField
import com.jueggs.jutils.extension.findMethod
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
        private val layoutIncluded: Boolean
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Any) {
            binding.setVariable(bindingVariableId, item)
            if (layoutIncluded) bindIncludedLayout(item)

            if (eventHandler != null) {
                val id = checkNotNull(eventHandlerVariableId) { "No variable id for eventhandler set. Override ${BaseDatabindingAdapter::getEventhandlerVariableId.name} to set a binding variable id or remove eventhandler" }
                binding.setVariable(id, eventHandler)
            }

            binding.executePendingBindings()
        }

        private fun bindIncludedLayout(item: Any) {
            val layoutField = binding.findField(INCLUDED_LAYOUT_ID_IDENTIFIER, Modifier.PUBLIC)
            val field = checkNotNull(layoutField) { "No included layout found although announced. Set ${BaseDatabindingAdapter::layoutIncluded.name} to false or include layout with id set to '@+id/$INCLUDED_LAYOUT_ID_IDENTIFIER')" }

            val includeBinding = field.get(binding)
            val setVariableMethod = includeBinding.findMethod(ViewDataBinding::setVariable.name, Modifier.PUBLIC)
            val method = checkNotNull(setVariableMethod) { "Method ${ViewDataBinding::setVariable.name} not found, check generated binding class(es)" }

            method.invoke(includeBinding, bindingVariableId, item)
        }
    }

    companion object {
        const val INCLUDED_LAYOUT_ID_IDENTIFIER = "include"
    }
}