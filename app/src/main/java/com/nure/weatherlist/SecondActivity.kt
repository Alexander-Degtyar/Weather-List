package com.nure.weatherlist

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*
import java.io.InputStreamReader
import java.net.URL

class SecondActivity : Activity() {
    private val key:String = "04b3238da4355087562489247181da68"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        getCity()?.let { edit_text.setText(it) }

        get_btn.setOnClickListener{
            val city = edit_text.text.toString()
            saveCity(city)
            Thread {
                val message = getWeather(city)
                val intent = Intent()
                // Variant 4
                intent.putExtra("weather4", message)
                setResult(RESULT_OK, intent)
                finish()
            }.start()
        }
    }

    private fun saveCity(value: String) {
        val pref = getPreferences(Context.MODE_PRIVATE)
        // Variant 4
        pref.edit()
            .putString("cityName4", value)
            .apply()
    }

    private fun getCity(): String? {
        // Variant 4
        return getPreferences(Context.MODE_PRIVATE).getString("cityName4", null)
    }

    fun getWeather(city: String): String? {
        val url = URL("http://api.openweathermap.org/data/2.5/weather?q=$city&lang=ru&appid=$key")
        val connection = url.openConnection()
        val reader = InputStreamReader(connection.getInputStream())
        val text = reader.readText()
        reader.close()
        return text
    }


}