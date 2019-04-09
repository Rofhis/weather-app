package com.rofhiwa.weatherapp.db

import android.content.Context
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rofhiwa.weatherapp.data.db.AppDatabase
import com.rofhiwa.weatherapp.data.db.entity.CityEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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

        val cityEntity = CityEntity(
            cityName = "Pretoria",
            region = "Gauteng",
            country = "South Africa",
            latitude = 23.987,
            longitude = -90.98
        )

        appDatabase.provideCityDao()
            .insert(cityEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .test()
            .assertValue { result ->
                result > 0L
            }
    }
}