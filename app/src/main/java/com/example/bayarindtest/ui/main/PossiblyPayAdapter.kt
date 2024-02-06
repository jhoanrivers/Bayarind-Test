package com.example.bayarindtest.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bayarindtest.R
import com.example.bayarindtest.databinding.ItemMoneyBinding

class PossiblyPayAdapter : RecyclerView.Adapter<PossiblyPayAdapter.ViewHolder>() {

    var possiblyPays = mutableListOf<String>()


    fun updatePossiblyPays(items: MutableList<String>) {
        possiblyPays = items
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemMoneyBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String, context: Context) {
            binding.tvValue.text = item

            if(item == MainUtils.UANG_PAS){
                binding.wrapperCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
            } else {
                binding.wrapperCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.gray))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMoneyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return possiblyPays.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = possiblyPays[position]
        holder.bind(item, holder.itemView.context)
    }
}