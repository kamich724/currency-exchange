package com.example.currencyexchanger.database

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

interface AppDataStore {
    companion object {
        const val FIRST_INSTALL = "first_install"

        fun get(): AppDataStore = DataStoreImpl

    }
    fun install(application: Application,name:String)

    fun getBoolean(key: String?, defValue: Boolean): Boolean
    fun putBoolean(key: String?, value: Boolean): AppDataStore

}

    object DataStoreImpl: AppDataStore {
        private lateinit var sharedPreferences: SharedPreferences

       override fun install(application: Application, name: String) {
            sharedPreferences = application.getSharedPreferences(name, Context.MODE_PRIVATE)
        }

       override  fun putBoolean(key: String?, value: Boolean): AppDataStore {
            sharedPreferences.edit().putBoolean(key, value).apply()
            return this
        }

        override fun getBoolean(key: String?, defValue: Boolean) = sharedPreferences.getBoolean(key, defValue)

    }