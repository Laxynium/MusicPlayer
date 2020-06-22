package com.musicplayer.framework.ui

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


abstract class DataBoundListAdapter<T>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, DataBoundViewHolder>(
    AsyncDifferConfig.Builder<T>(diffCallback)
        .build()
){
    private val viewHolders: MutableList<DataBoundViewHolder> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder {
        val binding = createBinding(parent, viewType)
        val viewHolder = DataBoundViewHolder(binding)
        binding.lifecycleOwner = viewHolder
        viewHolder.markCreated()
        viewHolders.add(viewHolder)

        return viewHolder
    }

    protected abstract fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding

    override fun onBindViewHolder(holder: DataBoundViewHolder, position: Int) {
        if (position < itemCount) {
            bind(holder.binding, getItem(position))
            holder.binding.executePendingBindings()
        }
    }

    protected abstract fun bind(binding: ViewDataBinding, item: T)

    override fun onViewAttachedToWindow(holder: DataBoundViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.markAttach()
    }

    override fun onViewDetachedFromWindow(holder: DataBoundViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.markDetach()
    }

    fun setLifecycleDestroyed() {
        viewHolders.forEach {
            it.markDestroyed()
        }
    }
}

class DataBoundViewHolder constructor(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root),
    LifecycleOwner {
    private val lifecycleRegistry = LifecycleRegistry(this)
    private var wasPaused = false
    init {
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }
    fun markCreated() {
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }
    fun markAttach() {
        if (wasPaused) {
            lifecycleRegistry.currentState = Lifecycle.State.RESUMED
            wasPaused = false
        } else {
            lifecycleRegistry.currentState = Lifecycle.State.STARTED
        }
    }
    fun markDetach() {
        wasPaused = true
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }
    fun markDestroyed() {
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }
    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}