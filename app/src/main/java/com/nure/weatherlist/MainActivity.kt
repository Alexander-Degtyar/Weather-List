package com.nure.weatherlist

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : Activity() {
    private var REQUEST_CODE: Int = 0
    private val weatherAdapter = WeatherAdapter()
    private lateinit var weatherDbHelper: WeatherDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.adapter = weatherAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)

        button.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }

        weatherDbHelper = WeatherDbHelper(this)
        initAdapterWithWeather()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // Variant 4
            val weather = data?.getStringExtra("weather4")
            weatherAdapter.add(weather.toString())
            weatherAdapter.notifyDataSetChanged()
            saveWeather(weather.toString())
        }
    }

    private fun saveWeather(s: String) {
        val writableDatabase = weatherDbHelper.writableDatabase
        val entry = ContentValues()
        entry.put(WeatherReaderContract.WeatherEntry.COLUMN_NAME_MESSAGE, s)
        writableDatabase.insert(WeatherReaderContract.WeatherEntry.TABLE_NAME, null, entry)
    }

    private fun initAdapterWithWeather() {
        val readableDatabase = weatherDbHelper.readableDatabase
        val cursor = readableDatabase.query(
                WeatherReaderContract.WeatherEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        )
        val weatherIndex = cursor.getColumnIndex(WeatherReaderContract.WeatherEntry.COLUMN_NAME_MESSAGE)
        while (cursor.moveToNext()) {
            val weatherText = cursor.getString(weatherIndex)
            weatherAdapter.add(weatherText)
        }
    }
}