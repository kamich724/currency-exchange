package com.example.currencyexchanger.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Rates::class,Currency::class],
    version = 1,
    exportSchema = false
)
abstract class RatesDatabase : RoomDatabase() {

    abstract fun rateDao():RatesDao

    abstract fun currencyDao():CurrencyDao
}