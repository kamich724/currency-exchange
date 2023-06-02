package com.example.currencyexchanger.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyexchanger.databinding.AdaptorItemExchangeRateBinding


class CurrencyAdaptor(
    private val context: Context
) :
    RecyclerView.Adapter<CurrencyAdaptor.Holder>() {
    val originalList = mutableListOf<CurrencyModel>()

    val data = mutableListOf<CurrencyModel>()

    inner class Holder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        run { Holder(AdaptorItemExchangeRateBinding.inflate(LayoutInflater.from(context), parent, false)) }


    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)

        if (holder.binding is AdaptorItemExchangeRateBinding) {
            holder.binding.tvCurrencyName.text=item.name
            holder.binding.tvCurrencyRate.text=String.format("%.2f", item.rate)
        }

    }

    override fun getItemCount() = data.size

    private fun getItem(position: Int) = this.data[position]



    fun submitList(newList: List<CurrencyModel>) {

        if (data.isEmpty()) {
            data.addAll(newList)
            notifyDataSetChanged()
        } else {

            //val oldList = mutableListOf<ChatMessage>().apply { addAll(data) }
            val oldList = ArrayList<CurrencyModel>(data)
            val oldCount = oldList.size - 1
            val diffResult = DiffUtil.calculateDiff(UserDiffUtils(newList, oldList))

            data.clear()
            data.addAll(newList)
            diffResult.dispatchUpdatesTo(this)


        }
    }

    inner class UserDiffUtils(
        private val newList: List<CurrencyModel>,
        val oldList: List<CurrencyModel>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            return oldItem.name == newItem.name
        }


        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            return super.getChangePayload(oldItemPosition, newItemPosition)
        }
    }
}