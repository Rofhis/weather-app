package com.rofhiwa.weatherapp.db

import android.content.Context
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rofhiwa.weatherapp.data.db.AppDatabase
import com.rofhiwa.weatherapp.data.db.entity.CityWeatherEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class TestCityDaoTest {

  private lateinit var appDatabase: AppDatabase

  @Before
  fun initDb() {
    val context = mock(Context::class.java)
    appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
  }

  @After
  @Throws(Exception::class)
  fun closeDb() {
    appDatabase.close()
  }

  @Test
  @Throws(Exception::class)
  fun testInsertData() {

    val cityWeatherEntity = CityWeatherEntity(
      cityName = "Pretoria",
      region = "Gauteng",
      country = "South Africa",
      latitude = 23.987,
      longitude = -90.98,
      temperatureMin = 17.2,
      temperatureMax = 3.2,
      pressure = 3.2,
      humidity = 33,
      clouds = 50,
      windSpeed = 56.4,
      windDirection = "NW",
      rain = 45,
      snow = 56,
      icon = "icon.png",
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