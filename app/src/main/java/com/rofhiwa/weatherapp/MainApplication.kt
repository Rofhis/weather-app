package com.rofhiwa.weatherapp

import android.app.Application
import com.facebook.stetho.Stetho
import com.rofhiwa.weatherapp.data.db.AppDatabase
import timber.log.Timber
import timber.log.Timber.DebugTree

class MainApplication : Application() {

  lateinit var appDatabase: AppDatabase

  override fun onCreate() {
    super.onCreate()
    appDatabase = AppDatabase.getInstance(this)
    Stetho.initializeWithDefaults(this)
    Timber.plant(DebugTree())
  }
}