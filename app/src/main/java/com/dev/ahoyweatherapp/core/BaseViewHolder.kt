package com.dev.ahoyweatherapp.core

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Bino on 2022-05-30
 */

open class BaseViewHolder<out T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(
    binding.root
)
