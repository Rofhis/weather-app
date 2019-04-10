package com.rofhiwa.weatherapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rofhiwa.weatherapp.data.db.dao.CityWeatherDao
import com.rofhiwa.weatherapp.data.db.entity.CityWeatherEntity

const val DATABASE_VERSION = 1
const val DATABASE_NAME = "weather_database"

@Database(
  entities = [
    CityWeatherEntity::class
  ],
  version = DATABASE_VERSION,
  exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

  abstract fun provideCityWeatherDao(): CityWeatherDao

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