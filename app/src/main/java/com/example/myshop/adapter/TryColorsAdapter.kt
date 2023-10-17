package com.example.myshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myshop.R
import com.example.myshop.databinding.RowTryColorsBinding
import com.example.myshop.models.TryColorsData

class TryColorsAdapter : RecyclerView.Adapter<TryColorsAdapter.ViewHolder>() {

    class ViewHolder(var binding: RowTryColorsBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<TryColorsData>() {
        override fun areItemsTheSame(oldItem: TryColorsData, newItem: TryColorsData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: TryColorsData,
            newItem: TryColorsData
        ): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            RowTryColorsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = differ.currentList[position]
        holder.binding.apply {
            Item1.setBackgroundColor(root.resources.getColor(getColor(list.try1),null))
            Item2.setBackgroundColor(root.resources.getColor(getColor(list.try2),null))
            Item3.setBackgroundColor(root.resources.getColor(getColor(list.try3),null))
            Item4.setBackgroundColor(root.resources.getColor(getColor(list.try4),null))
            Item5.setBackgroundColor(root.resources.getColor(getColor(list.try5),null))
        }

    }

    fun getColor(color: String): Int {
        return when (color) {
            "red" -> {
                R.color.red
            }

            "black" -> {
                R.color.black
            }

            "white" -> {
                R.color.white
            }

            "purple" -> {
                R.color.purple
            }

            "yellow" -> {
                R.color.yellow
            }

            "grey" -> {
                R.color.grey
            }

            "blue" -> {
                R.color.blue
            }

            else -> {
                R.color.green
            }
        }

    }

    override fun getItemCount(): Int = differ.currentList.size

}