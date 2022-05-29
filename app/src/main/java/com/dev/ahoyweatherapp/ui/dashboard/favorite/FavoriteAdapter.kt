package com.dev.ahoyweatherapp.ui.dashboard.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.dev.ahoyweatherapp.core.BaseAdapter
import com.dev.ahoyweatherapp.databinding.FavoriteItemBinding
import com.dev.ahoyweatherapp.databinding.ItemForecastBinding
import com.dev.ahoyweatherapp.db.entity.FavoriteEntity
import com.dev.ahoyweatherapp.domain.model.ListItem

/**
 * Created by Bino on 2022-05-30
 */

class FavoriteAdapter(
    private val callBack: (FavoriteEntity, View, View, View, View, View) -> Unit
) : BaseAdapter<FavoriteEntity>(diffCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = FavoriteItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val viewModel = FavoriteItemViewModel()
        mBinding.viewModel = viewModel

        mBinding.cardView.setOnClickListener {
            mBinding.viewModel?.item?.get()?.let { /*
                ViewCompat.setTransitionName(mBinding.cardView, mBinding.rootView.resources.getString(R.string.cardView, it.getDay()))
                ViewCompat.setTransitionName(mBinding.imageViewForecastIcon, mBinding.rootView.resources.getString(R.string.forecastIcon, it.getDay()))
                ViewCompat.setTransitionName(mBinding.textViewDayOfWeek, mBinding.rootView.resources.getString(R.string.dayOfWeek, it.getDay()))
                ViewCompat.setTransitionName(mBinding.textViewTemp, mBinding.rootView.resources.getString(R.string.temp, it.getDay()))
                ViewCompat.setTransitionName(mBinding.linearLayoutTempMaxMin, mBinding.rootView.resources.getString(R.string.tempMaxMin, it.getDay()))*/

//                callBack(
//                    it,
//                    mBinding.cardView,
//                    mBinding.imageViewForecastIcon,
//                    mBinding.textViewDayOfWeek,
//                    mBinding.textViewTemp,
//                    mBinding.linearLayoutTempMaxMin
//                )
            }
        }
        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as FavoriteItemBinding).viewModel?.item?.set(getItem(position))
        binding.executePendingBindings()
    }
}

val diffCallback = object : DiffUtil.ItemCallback<FavoriteEntity>() {
    override fun areContentsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean =
        oldItem.name == newItem.name
}
