package com.rofhiwa.weatherapp.ui.dashboard

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rofhiwa.weatherapp.BR
import com.rofhiwa.weatherapp.R
import com.rofhiwa.weatherapp.databinding.FragmentDashboardBinding
import com.rofhiwa.weatherapp.ui.base.BaseFragment
import com.google.android.material.appbar.AppBarLayout
import com.rofhiwa.weatherapp.data.db.entity.CityWeatherEntity
import kotlin.math.roundToInt

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {

  private lateinit var dashboardViewModel: DashboardViewModel

  private lateinit var dataBinding: FragmentDashboardBinding

  override fun getLayoutId(): Int = R.layout.fragment_dashboard

  override fun getViewModel(): DashboardViewModel = dashboardViewModel

  override fun getBindingVariable(): Int = BR.viewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    super.onCreateView(inflater, container, savedInstanceState)

    dataBinding = getViewDataBinding()

    dataBinding.collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE)

    dataBinding.appbarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
      dataBinding.collapsingToolbar.isTitleEnabled = Math.abs(verticalOffset) - appBarLayout.totalScrollRange == 0
    })

    dashboardViewModel.getWeatherFromDatabase("Rooihuiskraal")
    dashboardViewModel.weatherUpdateLiveData.observe(this, onWeatherUpdateObserver)

    return dataBinding.root
  }

  private val onWeatherUpdateObserver = Observer<CityWeatherEntity?> { cityWeather ->
    if (cityWeather != null) {
      dataBinding.collapsingToolbar.title = cityWeather.cityName
      dataBinding.txtCityName.text = cityWeather.cityName
      dataBinding.txtRegionAndCountryName.text = String.format("%s, %s", cityWeather.region, cityWeather.country)
      dataBinding.txtTemperature.text = String.format("%d%s", cityWeather.temperatureCelsius.roundToInt(), requireActivity().getString(R.string.degree_celsius))

      dataBinding.conditionIcon.setImageURI(Uri.parse(cityWeather.conditionIcon))

      dataBinding.conditionLabel.text = cityWeather.conditionLabel
      dataBinding.txtClouds.setText(cityWeather.clouds.toString())
      dataBinding.txtHumidity.setText(cityWeather.humidity.toString())
      dataBinding.txtLastUpdated.setText(cityWeather.lastUpdated)
      dataBinding.txtWindDegree.setText(cityWeather.windDegree.toString())
      dataBinding.txtWindDirection.setText(cityWeather.windDirection)
      dataBinding.txtWindSpeed.setText(cityWeather.windSpeed.toString())
    }
  }

  private fun setImage() {

  }
}
