package com.example.currencyexchanger.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "exchange_rates", indices = [Index(value = arrayOf("currency_name"), unique = true)])
class Rates(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "currency_name") val currencyName: String,
    @ColumnInfo(name = "rate") val rate: Double
)
