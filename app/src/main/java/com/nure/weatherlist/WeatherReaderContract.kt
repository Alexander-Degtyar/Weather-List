package com.nure.weatherlist

import android.provider.BaseColumns

object WeatherReaderContract {
    object WeatherEntry : BaseColumns {
        const val TABLE_NAME = "messages"
        const val COLUMN_NAME_MESSAGE = "text"
    }

    const val SQL_CREATE_ENTRIES = "CREATE TABLE ${WeatherEntry.TABLE_NAME} " +
            "(${BaseColumns._ID} INTEGER PRIMARY KEY, " +
            "${WeatherEntry.COLUMN_NAME_MESSAGE} TEXT)"
}