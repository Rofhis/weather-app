package com.rofhiwa.weatherapp.di.components

import android.app.Application
import com.rofhiwa.weatherapp.MainApplication
import com.rofhiwa.weatherapp.di.modules.ApplicationModule
import com.rofhiwa.weatherapp.di.modules.NetworkModule
import com.rofhiwa.weatherapp.services.LocationService
import com.rofhiwa.weatherapp.ui.dashboard.DashboardFragment
import com.rofhiwa.weatherapp.ui.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {
  fun inject(app: MainApplication)
  fun inject(activity: MainActivity)
  fun inject(fragment: DashboardFragment)
  fun inject(service: LocationService)

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(module: Application): Builder

    fun applicationModule(module: ApplicationModule): Builder
    fun networkModule(module: NetworkModule): Builder

    fun build(): ApplicationComponent
  }
}