package com.example.currencyexchanger.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrency(currency: Currency)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrencies(currencies: List<Currency>)

    @Transaction
    @Query("SELECT * FROM exchange_currency ORDER BY currency_name ASC")
    fun getAllCurrencies(): LiveData<List<Currency>>
}