package com.dev.ahoyweatherapp

import android.os.Build
import com.dev.ahoyweatherapp.dao.CitiesForSearchDaoTest
import com.dev.ahoyweatherapp.dao.CurrentWeatherDaoTest
import com.dev.ahoyweatherapp.dao.ForecastDaoTest
import com.dev.ahoyweatherapp.repo.CurrentWeatherRepositoryTest
import com.dev.ahoyweatherapp.repo.ForecastRepositoryTest
import com.dev.ahoyweatherapp.viewModel.DashboardViewModelTest
import com.dev.ahoyweatherapp.viewModel.SearchViewModelTest
import com.dev.ahoyweatherapp.viewModel.WeatherDetailViewModelTest
import com.dev.ahoyweatherapp.viewState.CurrentWeatherViewStateTest
import com.dev.ahoyweatherapp.viewState.ForecastViewStateTest
import com.dev.ahoyweatherapp.viewState.SearchViewStateTest
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.robolectric.annotation.Config

/**
 * Created by Bino on 2022-05-30
 */

@Config(sdk = [Build.VERSION_CODES.R])
@RunWith(Suite::class)
@Suite.SuiteClasses(
    CitiesForSearchDaoTest::class,
    CurrentWeatherDaoTest::class,
    CurrentWeatherViewStateTest::class,
    DashboardViewModelTest::class,
    ForecastDaoTest::class,
    ForecastViewStateTest::class,
    SearchViewStateTest::class,
    SearchViewModelTest::class,
    WeatherDetailViewModelTest::class,
    ForecastRepositoryTest::class,
    CurrentWeatherRepositoryTest::class
)
class TestSuite
