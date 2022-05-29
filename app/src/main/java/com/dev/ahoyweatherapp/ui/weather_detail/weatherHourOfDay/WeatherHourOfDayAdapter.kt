package com.dev.ahoyweatherapp.ui.weather_detail.weatherHourOfDay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.dev.ahoyweatherapp.core.BaseAdapter
import com.dev.ahoyweatherapp.databinding.ItemWeatherHourOfDayBinding
import com.dev.ahoyweatherapp.domain.model.ListItem

/**
 * Created by Bino on 2022-05-30
 */

class WeatherHourOfDayAdapter(private val callBack: (ListItem) -> Unit) : BaseAdapter<ListItem>(
    diffCallback
) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = ItemWeatherHourOfDayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val viewModel = WeatherHourOfDayItemViewModel()
        mBinding.viewModel = viewModel

        mBinding.rootView.setOnClickListener {
            mBinding.viewModel?.item?.get()?.let {
                callBack.invoke(it)
            }
        }
        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as ItemWeatherHourOfDayBinding).viewModel?.item?.set(getItem(position))
        binding.executePendingBindings()
    }
}

val diffCallback = object : DiffUtil.ItemCallback<ListItem>() {
    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem.dtTxt == newItem.dtTxt
}
