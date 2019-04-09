package com.rofhiwa.weatherapp.data.db

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rofhiwa.weatherapp.data.db.dao.CityDao
import com.rofhiwa.weatherapp.data.db.dao.WeatherDao
import com.rofhiwa.weatherapp.data.db.entity.CityEntity
import com.rofhiwa.weatherapp.data.db.entity.WeatherEntity

const val DATABASE_VERSION = 1
const val DATABASE_NAME = "weather_app_database"

@Database(
    entities = [
      CityEntity::class,
      WeatherEntity::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

  abstract fun provideCityDao(): CityDao
  abstract fun provideWeatherDao(): WeatherDao

  companion object {

    @Volatile
    private var instance: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
      return instance ?: synchronized(this) {
        instance ?: buildDatabase(context).also { instance = it }
      }
    }

    private fun buildDatabase(context: Context): AppDatabase {
      return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
          .allowMainThreadQueries()
          .build()
    }
  }
}