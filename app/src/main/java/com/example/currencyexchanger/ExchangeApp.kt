package com.example.currencyexchanger

import android.app.Application
import androidx.work.*
import com.example.currencyexchanger.api.ApiWorker
import com.example.currencyexchanger.database.AppDataStore
import com.example.currencyexchanger.database.CurrencyManager
import java.util.concurrent.TimeUnit


class ExchangeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppDataStore.get()
            .install(this, applicationContext.resources.getString(R.string.app_name))
        CurrencyManager.install(application = this)


        if (AppDataStore.get().getBoolean(AppDataStore.FIRST_INSTALL, false)) {
            AppUtil.runApi()
            AppDataStore.get().putBoolean(AppDataStore.FIRST_INSTALL, true)
        }

        startWorker()
    }

    private fun startWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val work = PeriodicWorkRequestBuilder<ApiWorker>(30, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "my_unique_worker",
            ExistingPeriodicWorkPolicy.KEEP, work
        )
    }
}