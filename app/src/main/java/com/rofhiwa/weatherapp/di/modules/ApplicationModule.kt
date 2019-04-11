package com.rofhiwa.weatherapp.di.modules

import android.app.Application
import android.content.Context.LOCATION_SERVICE
import android.location.LocationManager
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.rofhiwa.weatherapp.annotation.ForApplication
import com.rofhiwa.weatherapp.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class ApplicationModule(private val application: Application) {

  @Provides
  @Singleton
  @ForApplication
  fun provideApplication(): Application {
    return application
  }

  @Singleton
  @Provides
  fun provideDatabaseInstance(): AppDatabase {
    return AppDatabase.getInstance(application)
  }

  @Provides
  @Singleton
  fun provideLocationManager(): LocationManager {
    return application.getSystemService(LOCATION_SERVICE) as LocationManager
  }

  @Provides
  @Singleton
  fun provideLocalBroadcastManager(): LocalBroadcastManager {
    return LocalBroadcastManager.getInstance(application.applicationContext)
  }
}