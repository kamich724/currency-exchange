package com.example.currencyexchanger

import android.util.Log
import com.example.currencyexchanger.api.RetrofitHelper
import com.example.currencyexchanger.api.WebService
import com.example.currencyexchanger.database.Currency
import com.example.currencyexchanger.database.CurrencyManager
import com.example.currencyexchanger.database.Rates
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppUtil {
    companion object {
         fun runApi() {

            CoroutineScope(Dispatchers.IO).launch {
                kotlin.runCatching {
                    val api = RetrofitHelper.getInstance().create(WebService::class.java)
                    val result = api.getCurrenciesList()
                    if (result != null) {
                        val list = result.keys.toList()
                        val currencyList = list.map { Currency(0, it) }
                        CurrencyManager.get().getCurrencyDb().insertCurrencies(currencyList)
                        Log.d("kmjk", "getCurrenciesList: ${currencyList}")
                    }


                }.onFailure {
                    Log.e("kmjk", "onFailure: ${it.message}")
                }

            }


            CoroutineScope(Dispatchers.IO).launch {
                kotlin.runCatching {
                    val api = RetrofitHelper.getInstance().create(WebService::class.java)
                    val result = api.getExchangeRates()
                    if (result != null) {
                        val rates = result.rates
                        val list = rates.keys.toList()
                        val rateList = list.map { Rates(0, it, rates[it] ?: 0.0) }
                        CurrencyManager.get().getCurrencyDb().insertRates(rateList)
                        Log.d("kmjk", "getExchangeRates: ${rateList}")

                    }


                }.onFailure {
                    Log.e("kmjk", "onFailure: ${it.message}")
                }

            }
        }
    }
}