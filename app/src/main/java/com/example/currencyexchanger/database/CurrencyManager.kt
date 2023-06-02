package com.example.currencyexchanger.database

import android.app.Application
import androidx.room.Room

interface CurrencyManager {

    fun getCurrencyDb(): CurrencyDb

    companion object {
        private lateinit var sInstance: CurrencyManager

        fun get(): CurrencyManager = sInstance

        fun install(application: Application): CurrencyManager{
            sInstance= if (::sInstance.isInitialized) sInstance else ManagerImpl(application)
            return sInstance
        }


    }
}


private class ManagerImpl(
    application: Application
) : CurrencyManager {

    private val currencyDb: CurrencyDb

    private val database by lazy {
        Room.databaseBuilder(application, RatesDatabase::class.java, "database-rate")
            .build()
    }

    init {
        currencyDb = CurrencyDbImp(application, database)
    }

    override fun getCurrencyDb(): CurrencyDb = currencyDb


}