package com.example.currencyexchanger.database

import androidx.lifecycle.LiveData

interface CurrencyDb {

    fun getAllRates(): LiveData<List<Rates>>
    fun getRates(): List<Rates>
    fun insertRate(recentChat: Rates)
    fun insertRates(recentChats: List<Rates>)
    fun getRateByName(currencyName: String): Rates?


    fun insertCurrency(currency: Currency)
    fun insertCurrencies(currencies: List<Currency>)
    fun getAllCurrencies(): LiveData<List<Currency>>



}