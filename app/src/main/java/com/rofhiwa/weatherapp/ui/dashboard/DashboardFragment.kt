package com.rofhiwa.weatherapp.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.rofhiwa.weatherapp.BR
import com.rofhiwa.weatherapp.R
import com.rofhiwa.weatherapp.databinding.FragmentDashboardBinding
import com.rofhiwa.weatherapp.ui.base.BaseFragment
import com.google.android.material.appbar.AppBarLayout

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {

  private lateinit var dashboardViewModel: DashboardViewModel

  override fun getBindingVariable(): Int = BR.viewModel

  override fun getLayoutId(): Int = R.layout.fragment_dashboard

  override fun getViewModel(): DashboardViewModel = dashboardViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    super.onCreateView(inflater, container, savedInstanceState)

    val binding = getViewDataBinding()

    binding.collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE)

    binding.appbarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
      binding.collapsingToolbar.isTitleEnabled = Math.abs(verticalOffset) - appBarLayout.totalScrollRange == 0
    })

    dashboardViewModel.getWeatherFromNetwork("Pretoria")

    return binding.root
  }
}
