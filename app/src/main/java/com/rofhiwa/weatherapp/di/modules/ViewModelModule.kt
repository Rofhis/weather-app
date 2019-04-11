package com.rofhiwa.weatherapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rofhiwa.weatherapp.annotation.ViewModelKey
import com.rofhiwa.weatherapp.di.factory.DaggerViewModelFactory
import com.rofhiwa.weatherapp.ui.dashboard.DashboardViewModel
import com.rofhiwa.weatherapp.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

  @Binds
  internal abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelKey(MainViewModel::class)
  abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(DashboardViewModel::class)
  abstract fun bindDashboardViewModel(viewModel: DashboardViewModel): ViewModel
}