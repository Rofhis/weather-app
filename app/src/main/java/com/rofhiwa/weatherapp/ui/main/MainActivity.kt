package com.rofhiwa.weatherapp.ui.main

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.rofhiwa.weatherapp.BR
import com.rofhiwa.weatherapp.R
import com.rofhiwa.weatherapp.databinding.ActivityMainBinding
import com.rofhiwa.weatherapp.extensions.showLongToast
import com.rofhiwa.weatherapp.services.LocationService
import com.rofhiwa.weatherapp.ui.base.BaseActivity
import android.content.Context
import android.content.IntentFilter
import com.rofhiwa.weatherapp.R.string
import com.rofhiwa.weatherapp.services.ACTION_LOCATION_BROADCAST
import com.rofhiwa.weatherapp.services.EXTRA_LATITUDE
import com.rofhiwa.weatherapp.services.EXTRA_LONGITUDE

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

  private lateinit var mainViewModel: MainViewModel

  private lateinit var binding: ActivityMainBinding

  private val locationServiceIntent: Intent by lazy {
    Intent(this, LocationService::class.java)
  }

  private val broadcastReceiver: LocalBroadcastManager by lazy {
    LocalBroadcastManager.getInstance(this)
  }

  override fun getLayoutId(): Int = R.layout.activity_main

  override fun getViewModel(): MainViewModel = mainViewModel

  override fun getBindingVariable(): Int = BR.viewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    super.onCreate(savedInstanceState)
    binding = getViewDataBinding()

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
      startService(locationServiceIntent)
    } else {
      if (hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
        startService(locationServiceIntent)
      } else {
        requestPermissionsSafely(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), LOCATION_REQUEST_CODE)
      }
    }
  }

  override fun onResume() {
    super.onResume()
    broadcastReceiver.registerReceiver(locationBroadcastReceiver, IntentFilter(ACTION_LOCATION_BROADCAST))
  }

  override fun onPause() {
    super.onPause()
    broadcastReceiver.unregisterReceiver(locationBroadcastReceiver)
  }

  override fun onDestroy() {
    super.onDestroy()
    stopService(locationServiceIntent)
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
    when (requestCode) {
      LOCATION_REQUEST_CODE -> {
        if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
          startService(locationServiceIntent)
        } else {
          applicationContext.showLongToast(getString(string.msg_location_permission_required))
        }
        return
      }
    }
  }

  private val locationBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
      intent?.let {
        if (it.action == ACTION_LOCATION_BROADCAST) {
          val latitude = it.extras?.getDouble(EXTRA_LATITUDE)
          val longitude = it.extras?.getDouble(EXTRA_LONGITUDE)
          mainViewModel.getWeatherFromNetwork("$latitude,$longitude")
        }
      }
    }
  }

  companion object {
    const val LOCATION_REQUEST_CODE = 201
  }
}
