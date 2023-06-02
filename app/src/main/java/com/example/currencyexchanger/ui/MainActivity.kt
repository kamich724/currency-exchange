package com.example.currencyexchanger.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.currencyexchanger.R
import com.example.currencyexchanger.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    var dialog: ProgressDialog? = null

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[CurrencyViewModel::class.java]
    }
    private val adaptor by lazy {
        CurrencyAdaptor(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        dialog = ProgressDialog(this)
        dialog?.setMessage(resources.getString(R.string.loading_wait))
        dialog?.setCancelable(false)
        dialog?.show()
        binding.exchangeList.adapter = adaptor


        initObserver()
        initListener()

    }

    private fun initListener() {
        viewModel.currencies.observe(this) {
            if (!it.isNullOrEmpty()) {
                viewModel.currencyList.addAll(it.map { it.currencyName })
            }
            dialog?.dismiss()
        }
    }

    private fun initObserver() {
        binding.dropdownCurrency.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    val item = parent.getItemAtPosition(position).toString()
                    if (binding.etCurrency.text.isNotEmpty()) {
                        viewModel.getExchangeRate(
                            item,
                            binding.etCurrency.text.toString().toDouble()
                        )
                    }else{
                        viewModel.exchangeList.clear()
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }


        binding.etCurrency.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (s.isNotEmpty()) {
                    viewModel.getExchangeRate(
                        binding.dropdownCurrency.selectedItem.toString(),
                        s.toString().toDouble()
                    )
                }else{
                    viewModel.exchangeList.clear()
                }
            }
        })
    }
}