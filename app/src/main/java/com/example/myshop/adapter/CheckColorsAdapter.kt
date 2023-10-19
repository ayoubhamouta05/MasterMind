package com.example.myshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myshop.R
import com.example.myshop.databinding.RowCheckColorsBinding
import com.example.myshop.models.CheckColorsData

class CheckColorsAdapter : RecyclerView.Adapter<CheckColorsAdapter.ViewHolder>() {

    class ViewHolder(var binding: RowCheckColorsBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<CheckColorsData>() {
        override fun areItemsTheSame(oldItem: CheckColorsData, newItem: CheckColorsData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CheckColorsData,
            newItem: CheckColorsData
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
            RowCheckColorsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = differ.currentList[position]
        holder.binding.apply {
            if (list.check1 == 1){
                Item1.setBackgroundColor(root.resources.getColor(R.color.white,null))
            }else if (list.check1 == 2) {
                Item1.setBackgroundColor(root.resources.getColor(R.color.red, null))
            }else{
                Item1.background = root.resources.getDrawable(R.drawable._circular_img_background,null)
            }

            if (list.check2 == 1){
                Item2.setBackgroundColor(root.resources.getColor(R.color.white,null))
            }else if (list.check2 == 2) {
                Item2.setBackgroundColor(root.resources.getColor(R.color.red, null))
            }else{
                Item2.background = root.resources.getDrawable(R.drawable._circular_img_background,null)
            }

            if (list.check3 == 1){
                Item3.setBackgroundColor(root.resources.getColor(R.color.white,null))
            }else if (list.check3 == 2) {
                Item3.setBackgroundColor(root.resources.getColor(R.color.red, null))
            }else{
                Item3.background = root.resources.getDrawable(R.drawable._circular_img_background,null)
            }

            if (list.check4 == 1){
                Item4.setBackgroundColor(root.resources.getColor(R.color.white,null))
            }else if (list.check4 == 2) {
                Item4.setBackgroundColor(root.resources.getColor(R.color.red, null))
            }else{
                Item4.background = root.resources.getDrawable(R.drawable._circular_img_background,null)
            }

            if (list.check5 == 1){
                Item5.setBackgroundColor(root.resources.getColor(R.color.white,null))
            }else if (list.check5 == 2) {
                Item5.setBackgroundColor(root.resources.getColor(R.color.red, null))
            }else{
                Item5.background = root.resources.getDrawable(R.drawable._circular_img_background,null)
            }
        }
    }


    override fun getItemCount(): Int = differ.currentList.size

}