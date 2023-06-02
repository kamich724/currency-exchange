package com.example.currencyexchanger.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface RatesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRate(rate: Rates)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRates(rates: List<Rates>)

    @Transaction
    @Query("SELECT * FROM exchange_rates ORDER BY currency_name ASC")
    fun getAllRates(): LiveData<List<Rates>>
    @Transaction

    @Query("SELECT * FROM exchange_rates ORDER BY currency_name ASC")
    fun getRates(): List<Rates>

    @Query("SELECT * FROM exchange_rates  where currency_name =:currencyName ")
    fun getRateByName(currencyName: String): Rates?
}