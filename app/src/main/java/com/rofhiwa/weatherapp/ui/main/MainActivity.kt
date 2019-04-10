package com.rofhiwa.weatherapp.ui.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.rofhiwa.weatherapp.BR
import com.rofhiwa.weatherapp.R
import com.rofhiwa.weatherapp.databinding.ActivityMainBinding
import com.rofhiwa.weatherapp.extensions.showLongToast
import com.rofhiwa.weatherapp.services.LocationService
import com.rofhiwa.weatherapp.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

  private lateinit var mainViewModel: MainViewModel

  private lateinit var binding: ActivityMainBinding

  override fun getBindingVariable(): Int = BR.viewModel

  override fun getLayoutId(): Int = R.layout.activity_main

  override fun getViewModel(): MainViewModel = mainViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
      startService(Intent(this, LocationService::class.java))
    } else {
      if (hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
        startService(Intent(this, LocationService::class.java))
      } else {
        requestPermissionsSafely(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), LOCATION_REQUEST_CODE)
      }
    }
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
    when (requestCode) {
      LOCATION_REQUEST_CODE -> {
        if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
          startService(Intent(this, LocationService::class.java))
        } else {
          applicationContext.showLongToast("This app requires location to be able to work")
        }
        return
      }
    }
  }

  companion object {
    const val LOCATION_REQUEST_CODE = 201
  }
}
