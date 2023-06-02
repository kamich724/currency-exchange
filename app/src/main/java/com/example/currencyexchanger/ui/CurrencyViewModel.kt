package com.example.currencyexchanger.ui

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.example.currencyexchanger.database.CurrencyManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CurrencyViewModel : ViewModel() {

    private val currencyManager = CurrencyManager.get()


    val currencies = currencyManager.getCurrencyDb().getAllCurrencies()
    val currencyList = ObservableArrayList<String>()
    val exchangeList = ObservableArrayList<CurrencyModel>()


    fun getExchangeRate(currencyName: String, currencyValue: Double) {
        CoroutineScope(Default).launch {
            val selectedCurrency = currencyManager.getCurrencyDb().getRateByName(currencyName)
            if (selectedCurrency != null) {
                val newList = mutableListOf<CurrencyModel>()

                val conversionRate = (1 / selectedCurrency.rate)
                newList.add(CurrencyModel("USD", conversionRate.times(currencyValue)))
                val ratesList = currencyManager.getCurrencyDb().getRates()
                for (rates in ratesList) {
                    kotlin.runCatching {
                        if (rates.currencyName != selectedCurrency.currencyName) {
                            val rate = (rates.rate.times(conversionRate)).times(currencyValue)
                            newList.add(CurrencyModel(rates.currencyName, rate))
                        }
                    }.onFailure {
                        newList.add(CurrencyModel(rates.currencyName, 0.0))

                    }
                }
                withContext(Main) {
                    exchangeList.clear()
                    exchangeList.addAll(newList)
                }
            } else {
                exchangeList.clear()
            }
        }

    }

}