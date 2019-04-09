package com.rofhiwa.weatherapp

import android.app.Application
import com.rofhiwa.weatherapp.data.db.AppDatabase

class MainApplication : Application() {

    lateinit var appDatabase: AppDatabase

    override fun onCreate() {
        super.onCreate()
        appDatabase = AppDatabase.getInstance(this)
    }
}