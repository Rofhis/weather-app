package com.rofhiwa.weatherapp.db

import android.content.Context
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.rofhiwa.weatherapp.data.db.AppDatabase
import com.rofhiwa.weatherapp.data.db.entity.CityWeatherEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class CityWeatherDaoTest {

  private lateinit var appDatabase: AppDatabase

  @Before
  fun initDb() {
    val context = mock(Context::class.java)
    appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
    appDatabase = Room.inMemoryDatabaseBuilder(
      InstrumentationRegistry.getInstrumentation().context,
      AppDatabase::class.java
    ).build()
  }

  @After
  fun closeDb() {
    appDatabase.close()
  }

  @Test
  fun insertCityWeather() {

    val cityWeatherEntity = CityWeatherEntity(
      cityId = 1L,
      cityName = "Pretoria",
      region = "Gauteng",
      country = "South Africa",
      latitude = 23.987,
      longitude = -90.98,
      temperatureCelsius = 14.0,
      pressure = 3.2,
      humidity = 33,
      clouds = 50,
      windSpeed = 56.4,
      windDegree = 45,
      windDirection = "NW",
      conditionIcon = "icon.png",
      conditionCode = 343,
      conditionLabel = "Rainy",
      isDay = 2,
      lastUpdated = "2019/04/10 12:00:02"
    )

    appDatabase.provideCityWeatherDao()
      .insert(cityWeatherEntity)
      .test()
      .assertValue { result ->
        result > 0L
      }
  }
}