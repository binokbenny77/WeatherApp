package com.dev.ahoyweatherapp.core

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.dev.ahoyweatherapp.R

/**
 * Created by Bino on 2022-05-30
 */
abstract class BaseAdapter<T>(callback: DiffUtil.ItemCallback<T>) : ListAdapter<T, BaseViewHolder<ViewDataBinding>>(
    callback
) {

    override fun onBindViewHolder(holder: BaseViewHolder<ViewDataBinding>, position: Int) {
        (holder as BaseViewHolder<*>).binding.root.setTag(R.string.position, position)
        bind(holder.binding, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = getViewHolder(
        parent,
        viewType
    )

    open fun getViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder(
        createBinding(parent, viewType)
    )

    abstract fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding

    protected abstract fun bind(binding: ViewDataBinding, position: Int)
}
