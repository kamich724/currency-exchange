package com.example.currencyexchanger.api

import android.content.Context
import android.net.ConnectivityManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.currencyexchanger.AppUtil


class ApiWorker(private val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {

        return if (isInternetConnected(context)) {
            AppUtil.runApi()
            Result.success()
        } else {
            Result.retry()
        }

    }



    private fun isInternetConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetwork != null && cm.getNetworkCapabilities(cm.activeNetwork) != null
    }
}