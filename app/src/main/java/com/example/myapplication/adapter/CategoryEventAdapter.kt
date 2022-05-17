package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.dataClass.CategoryEventModelClass
import com.example.myapplication.databinding.CategoryEventsCardViewBinding

class CategoryEventAdapter(
    private val arrayList: ArrayList<CategoryEventModelClass>) :
    RecyclerView.Adapter<CategoryEventAdapter.ViewHolder>() {

    private lateinit var binding: CategoryEventsCardViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = CategoryEventsCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(binding: CategoryEventsCardViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            binding.tvCategoryName.text = arrayList[position].eventType
            binding.imgCategoryEvent .setImageResource(arrayList[position].eventTypeImage)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}