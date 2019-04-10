package com.rofhiwa.weatherapp

import android.app.Application
import com.facebook.stetho.Stetho
import com.rofhiwa.weatherapp.data.db.AppDatabase

class MainApplication : Application() {

  lateinit var appDatabase: AppDatabase

  override fun onCreate() {
    super.onCreate()
    appDatabase = AppDatabase.getInstance(this)

    if (BuildConfig.DEBUG) {
      Stetho.initializeWithDefaults(this)
    }
  }
}