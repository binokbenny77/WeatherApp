package com.dev.ahoyweatherapp.ui.search.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.dev.ahoyweatherapp.R
import com.dev.ahoyweatherapp.core.BaseAdapter
import com.dev.ahoyweatherapp.databinding.ItemSearchResultBinding
import com.dev.ahoyweatherapp.db.dao.CurrentWeatherDao
import com.dev.ahoyweatherapp.db.dao.FavoriteDao
import com.dev.ahoyweatherapp.db.entity.CitiesForSearchEntity
import com.dev.ahoyweatherapp.db.entity.CoordEntity
import com.dev.ahoyweatherapp.domain.datasource.forecast.ForecastLocalDataSource
import com.dev.ahoyweatherapp.domain.datasource.searchCities.SearchCitiesLocalDataSource

/**
 * Created by Bino on 2022-05-30
 */

class SearchResultAdapter(private val callBack: (CitiesForSearchEntity) -> Unit) :
    BaseAdapter<CitiesForSearchEntity>(
        diffCallback
    ) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = ItemSearchResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val viewModel = SearchResultItemViewModel()
        mBinding.viewModel = viewModel

        mBinding.rootView.setOnClickListener {
            mBinding.viewModel?.item?.get()?.let {
                callBack.invoke(it)
            }
        }
        mBinding.imAddtoFavorite.setOnClickListener {
            mBinding.viewModel?.item?.get()?.let {


                val city = CitiesForSearchEntity(
                    country = it.country,
                    importance = it.importance,
                    administrative = it.administrative,
                    coord = it.coord,
                    name = it.name,
                    county = it.county,
                    favorite = "true",
                    id = it.id
                )
                callBack.invoke(city)
            }
            mBinding.imAddtoFavorite.setImageResource(R.drawable.ic_star_filled)
        }


        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as ItemSearchResultBinding).viewModel?.item?.set(getItem(position))
        binding.executePendingBindings()
    }
}

val diffCallback = object : DiffUtil.ItemCallback<CitiesForSearchEntity>() {
    override fun areContentsTheSame(
        oldItem: CitiesForSearchEntity,
        newItem: CitiesForSearchEntity
    ): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(
        oldItem: CitiesForSearchEntity,
        newItem: CitiesForSearchEntity
    ): Boolean =
        oldItem.name == newItem.name
}
