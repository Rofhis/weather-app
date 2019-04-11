package com.rofhiwa.weatherapp

import android.app.Application
import com.facebook.stetho.Stetho
import com.rofhiwa.weatherapp.di.components.ApplicationComponent
import com.rofhiwa.weatherapp.di.components.DaggerApplicationComponent
import com.rofhiwa.weatherapp.di.modules.ApplicationModule
import com.rofhiwa.weatherapp.di.modules.NetworkModule

class MainApplication : Application() {

  lateinit var component: ApplicationComponent

  override fun onCreate() {
    super.onCreate()

    component = DaggerApplicationComponent
      .builder()
      .application(this)
      .applicationModule(ApplicationModule(this))
      .networkModule(NetworkModule())
      .build()
    component.inject(this)

    if (BuildConfig.DEBUG) {
      Stetho.initializeWithDefaults(this)
    }
  }
}