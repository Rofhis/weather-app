package com.rofhiwa.weatherapp.data.network.listener

interface NetworkDataListener<T> {
  fun onNetworkSuccess(data: T) {}
  fun onNetworkError(throwable: Throwable) {}
}