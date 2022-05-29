package com.dev.ahoyweatherapp.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.dev.ahoyweatherapp.R
import com.dev.ahoyweatherapp.core.BaseActivity
import com.dev.ahoyweatherapp.databinding.ActivityMainBinding
import com.dev.ahoyweatherapp.utils.extensions.hide
import com.dev.ahoyweatherapp.utils.extensions.show
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
/**
 * Created by Bino on 2022-05-30
 */
@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>(
    R.layout.activity_main,
    MainActivityViewModel::class.java
), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setTransparentStatusBar()

    }

    private fun setTransparentStatusBar() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuItemSearch -> {
                findNavController(R.id.container_fragment).navigate(R.id.searchFragment)
                true
            }
            else -> false
        }
    }



    override fun onBackPressed() {
            super.onBackPressed()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        return item.onNavDestinationSelected(findNavController(R.id.container_fragment)) || super.onOptionsItemSelected(
            item
        )
    }
}
