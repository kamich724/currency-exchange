package com.example.currencyexchanger.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyexchanger.database.Currency

@BindingAdapter("bindCurrencies")
fun bindCurrencies(recyclerView: RecyclerView, data: List<CurrencyModel>?) {
     val adapter = recyclerView.adapter as CurrencyAdaptor
     data?.let {  adapter.submitList(data) }
}