package com.rofhiwa.weatherapp.services

import android.app.Service
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.os.IBinder
import android.location.LocationManager
import androidx.localbroadcastmanager.content.LocalBroadcastManager

const val ACTION_LOCATION_BROADCAST = "location"
const val EXTRA_LATITUDE = "latitude"
const val EXTRA_LONGITUDE = "longitude"

class LocationService : Service(), LocationListener {

  private val locationManager: LocationManager by lazy {
    getSystemService(LOCATION_SERVICE) as LocationManager
  }

  private val broadcastReceiver: LocalBroadcastManager by lazy {
    LocalBroadcastManager.getInstance(applicationContext)
  }

  private val newIntent: Intent by lazy {
    Intent(ACTION_LOCATION_BROADCAST)
  }

  @Throws(SecurityException::class)
  fun getCurrentLocation(): Location? {

    val provider = LocationManager.NETWORK_PROVIDER
    if (locationManager.isProviderEnabled(provider)) {
      locationManager.requestLocationUpdates(provider, MIN_TIME_FOR_UPDATE, MIN_DISTANCE_FOR_UPDATE, this)
      return locationManager.getLastKnownLocation(provider)
    }
    return null
  }

  override fun onCreate() {
    super.onCreate()

    getCurrentLocation()?.let { location ->
      newIntent.putExtra(EXTRA_LATITUDE, location.latitude)
      newIntent.putExtra(EXTRA_LONGITUDE, location.longitude)
      broadcastReceiver.sendBroadcast(newIntent)
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    locationManager.removeUpdates(this)
  }

  override fun onBind(intent: Intent): IBinder? {
    return null
  }

  override fun onLocationChanged(location: Location?) {
    location?.let {
      newIntent.putExtra(EXTRA_LATITUDE, it.latitude)
      newIntent.putExtra(EXTRA_LONGITUDE, it.longitude)
      broadcastReceiver.sendBroadcast(newIntent)
    }
  }

  override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {}

  override fun onProviderEnabled(p0: String?) {}

  override fun onProviderDisabled(p0: String?) {}

  companion object {
    private const val MIN_DISTANCE_FOR_UPDATE: Float = 10f
    private const val MIN_TIME_FOR_UPDATE = (1000 * 60 * 1).toLong()
  }
}
