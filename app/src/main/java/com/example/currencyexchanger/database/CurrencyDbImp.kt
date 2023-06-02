package com.example.currencyexchanger.database

import android.app.Application
import androidx.lifecycle.LiveData

class CurrencyDbImp(val application: Application, database: RatesDatabase) : CurrencyDb {

    private val ratesDao: RatesDao
    private val currencyDao: CurrencyDao

    init {
        ratesDao = database.rateDao()
        currencyDao = database.currencyDao()
    }

    override fun getAllRates(): LiveData<List<Rates>> = ratesDao.getAllRates()
    override fun getRates(): List<Rates> = ratesDao.getRates()


    override fun insertRate(rate: Rates) = ratesDao.insertRate(rate)

    override fun insertRates(rates: List<Rates>) = ratesDao.insertRates(rates)
    override fun getRateByName(currencyName: String): Rates? = ratesDao.getRateByName(currencyName)

    override fun insertCurrency(currency: Currency) = currencyDao.insertCurrency(currency)

    override fun insertCurrencies(currencies: List<Currency>) =
        currencyDao.insertCurrencies(currencies)

    override fun getAllCurrencies(): LiveData<List<Currency>> = currencyDao.getAllCurrencies()

}
