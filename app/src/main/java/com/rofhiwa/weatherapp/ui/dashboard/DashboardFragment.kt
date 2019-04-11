package com.rofhiwa.weatherapp.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.request.RequestOptions
import com.rofhiwa.weatherapp.BR
import com.rofhiwa.weatherapp.R
import com.rofhiwa.weatherapp.databinding.FragmentDashboardBinding
import com.rofhiwa.weatherapp.ui.base.BaseFragment
import com.google.android.material.appbar.AppBarLayout
import com.rofhiwa.weatherapp.data.db.entity.CityWeatherEntity
import com.rofhiwa.weatherapp.di.components.ApplicationComponent
import com.rofhiwa.weatherapp.di.factory.DaggerViewModelFactory
import com.rofhiwa.weatherapp.ui.main.MainActivity
import com.rofhiwa.weatherapp.ui.main.MainViewModel
import com.rofhiwa.weatherapp.utils.GlideApp
import javax.inject.Inject

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {

  @Inject
  lateinit var viewModeFactory: DaggerViewModelFactory

  private lateinit var dashboardViewModel: DashboardViewModel

  private lateinit var mainViewModel: MainViewModel

  private lateinit var dataBinding: FragmentDashboardBinding

  private lateinit var mainActivity: MainActivity

  override fun getLayoutId(): Int = R.layout.fragment_dashboard

  override fun getViewModel(): DashboardViewModel = dashboardViewModel

  override fun getBindingVariable(): Int = BR.viewModel

  override fun performDependencyInjection(component: ApplicationComponent) {
    component.inject(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    mainActivity = requireActivity() as MainActivity
    
    dashboardViewModel = ViewModelProviders.of(this, viewModeFactory).get(DashboardViewModel::class.java)
    mainViewModel = ViewModelProviders.of(mainActivity, viewModeFactory).get(MainViewModel::class.java)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    super.onCreateView(inflater, container, savedInstanceState)

    dataBinding = getViewDataBinding()

    dataBinding.collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE)

    dataBinding.appbarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
      dataBinding.collapsingToolbar.isTitleEnabled = Math.abs(verticalOffset) - appBarLayout.totalScrollRange == 0
    })

    dashboardViewModel.weatherDatabaseRetrieveLiveData.observe(viewLifecycleOwner, onWeatherDatabaseRetrieveObserver)
    mainViewModel.newWeatherFoundLiveData.observe(viewLifecycleOwner, onNewWeatherFoundObserver)
    dashboardViewModel.getWeatherFromDatabase()

    return dataBinding.root
  }

  private val onNewWeatherFoundObserver = Observer<Boolean> { hasNewWeather ->
    if (hasNewWeather) {
      dashboardViewModel.getWeatherFromDatabase()
    }
  }

  private val onWeatherDatabaseRetrieveObserver = Observer<CityWeatherEntity?> { cityWeather ->
    if (cityWeather != null) {
      dataBinding.collapsingToolbar.title = cityWeather.cityName
      dataBinding.txtCityName.text = cityWeather.cityName
      dataBinding.txtRegionAndCountryName.text = String.format("%s, %s", cityWeather.region, cityWeather.country)
      dataBinding.txtTemperature.text = String.format("%.1f%s", cityWeather.temperatureCelsius, requireActivity().getString(R.string.degree_celsius))
      dataBinding.conditionLabel.text = cityWeather.conditionLabel
      dataBinding.txtClouds.setText(String.format("%d%s", cityWeather.clouds, requireActivity().getString(R.string.percentage_sign)))
      dataBinding.txtHumidity.setText(String.format("%d%s", cityWeather.humidity, requireActivity().getString(R.string.percentage_sign)))
      dataBinding.txtLastUpdated.setText(cityWeather.lastUpdated)
      dataBinding.txtWindDegree.setText(String.format("%d%s", cityWeather.windDegree, requireActivity().getString(R.string.degree)))
      dataBinding.txtWindDirection.setText(cityWeather.windDirection)
      dataBinding.txtWindSpeed.setText(String.format("%.1fkm/h", cityWeather.windSpeed))

      GlideApp.with(dataBinding.conditionIcon)
        .applyDefaultRequestOptions(RequestOptions().centerCrop())
        .load("https:${cityWeather.conditionIcon}")
        .into(dataBinding.conditionIcon)

      mainActivity.hideProgressBar()
    }
  }
}
