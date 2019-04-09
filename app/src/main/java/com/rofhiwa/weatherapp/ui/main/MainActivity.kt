package com.rofhiwa.weatherapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rofhiwa.weatherapp.R
import com.rofhiwa.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

    setSupportActionBar(binding.mainToolbar)

    actionBar?.title = getString(R.string.app_name)

  }
}
