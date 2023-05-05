package com.kg.love_calculator_beta.ui.history.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kg.love_calculator_beta.databinding.ItemHistoryBinding
import com.kg.love_calculator_beta.model.Love
import kotlin.properties.Delegates

class LoveAdapter(var onItemClick: ((Love) -> Unit)? = null) : androidx.recyclerview.widget.RecyclerView.Adapter<LoveAdapter.LoveViewHolder>() {

    private val data: ArrayList<Love> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun addList(love: List<Love>) {
        data.clear()
        data.addAll(love)
        notifyDataSetChanged()
    }

    inner class LoveViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(love: Love) {
            binding.apply {
                tvFirstName.text = love.firstName
                tvSecondName.text = love.secondName
                tvPercentage.text = love.percentage + "%"
            }
            itemView.setOnClickListener {
                onItemClick?.invoke(data[adapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoveViewHolder {
        return LoveViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: LoveViewHolder, position: Int) {
        holder.bind(data[position])
    }
}