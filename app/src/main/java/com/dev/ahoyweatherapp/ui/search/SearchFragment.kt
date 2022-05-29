package com.dev.ahoyweatherapp.ui.search

import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.ahoyweatherapp.R
import com.dev.ahoyweatherapp.core.BaseFragment
import com.dev.ahoyweatherapp.databinding.FragmentSearchBinding
import com.dev.ahoyweatherapp.db.entity.CitiesForSearchEntity
import com.dev.ahoyweatherapp.db.entity.FavoriteEntity
import com.dev.ahoyweatherapp.domain.datasource.favorite.FavoriteLocalDataSource
import com.dev.ahoyweatherapp.domain.datasource.forecast.ForecastLocalDataSource
import com.dev.ahoyweatherapp.domain.usecase.SearchCitiesUseCase
import com.dev.ahoyweatherapp.ui.main.MainActivity
import com.dev.ahoyweatherapp.ui.search.result.SearchResultAdapter
import com.dev.ahoyweatherapp.utils.extensions.hideKeyboard
import com.dev.ahoyweatherapp.utils.extensions.observeWith
import com.dev.ahoyweatherapp.utils.extensions.tryCatch
import dagger.hilt.android.AndroidEntryPoint
/**
 * Created by Bino on 2022-05-30
 */
@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>(
    R.layout.fragment_search,
    SearchViewModel::class.java,
) {

    override fun init() {
        super.init()
        initSearchResultsAdapter()
        initSearchView()

        binding.viewModel?.getSearchViewState()?.observeWith(
            viewLifecycleOwner
        ) {
            binding.viewState = it
            it.data?.let { results -> initSearchResultsRecyclerView(results) }
        }
    }

    private fun initSearchView() {
        val searchEditText: EditText = binding.searchView.findViewById(R.id.search_src_text)
        activity?.applicationContext?.let { ContextCompat.getColor(it, R.color.mainTextColor) }
            ?.let { searchEditText.setTextColor(it) }
        activity?.applicationContext?.let {
            ContextCompat.getColor(
                it,
                android.R.color.darker_gray
            )
        }
            ?.let { searchEditText.setHintTextColor(it) }
        binding.searchView.isActivated = true
        binding.searchView.setIconifiedByDefault(false)
        binding.searchView.isIconified = false

        val searchViewSearchIcon = binding.searchView.findViewById<ImageView>(R.id.search_mag_icon)
        searchViewSearchIcon.setImageResource(R.drawable.ic_search)

        binding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(newText: String): Boolean {
                    if (newText.isNotEmpty() && newText.count() > 2) {
                        binding.viewModel?.setSearchParams(
                            SearchCitiesUseCase.SearchCitiesParams(newText)
                        )
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText?.isNotEmpty() == true && newText.count() > 2) {
                        binding.viewModel?.setSearchParams(
                            SearchCitiesUseCase.SearchCitiesParams(newText)
                        )
                    }
                    return true
                }
            }
        )
    }

    private fun initSearchResultsAdapter() {
        val adapter = SearchResultAdapter { item ->

            if (item.favorite=="true")
            {
                viewModel.insertFavoriteList(item )
               // viewModel.fetchFavorite()
            }
            else
            {
                item.coord?.let {

                    binding.viewModel?.saveCoordsToSharedPref(it)
                        ?.subscribe { _, _ ->

                            tryCatch(
                                tryBlock = {
                                    binding.searchView.hideKeyboard((activity as MainActivity))
                                }
                            )

                            findNavController().navigate(
                                R.id.action_searchFragment_to_dashboardFragment
                            )
                        }
                }
            }

        }

        binding.recyclerViewSearchResults.adapter = adapter
        binding.recyclerViewSearchResults.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun initSearchResultsRecyclerView(list: List<CitiesForSearchEntity>) {
        (binding.recyclerViewSearchResults.adapter as SearchResultAdapter).submitList(
            list.distinctBy { it.getFullName() }.sortedBy { it.importance }
        )
    }
}
