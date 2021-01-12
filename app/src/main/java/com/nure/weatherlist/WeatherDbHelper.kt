package com.nure.weatherlist

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class WeatherDbHelper(context: Context?) : SQLiteOpenHelper(context, "MessageDB", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(WeatherReaderContract.SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        
    }
}