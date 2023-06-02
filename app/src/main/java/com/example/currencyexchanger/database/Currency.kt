package com.example.currencyexchanger.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "exchange_currency", indices = [Index(value = arrayOf("currency_name"), unique = true)])
class Currency(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "currency_name") val currencyName: String
)
