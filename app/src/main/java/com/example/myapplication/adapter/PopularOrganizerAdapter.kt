package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.dataClass.PopularOrganizerModelClass
import com.example.myapplication.databinding.PopularOrganizerCardViewBinding

class PopularOrganizerAdapter(
    private val arrayList: ArrayList<PopularOrganizerModelClass>) :
    RecyclerView.Adapter<PopularOrganizerAdapter.ViewHolder>() {

    private lateinit var binding: PopularOrganizerCardViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = PopularOrganizerCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(binding: PopularOrganizerCardViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            with(binding) {
                binding.tvOrganizerName.text = arrayList[position].organizerName
                imgOrganizer.setImageResource(arrayList[position].organizerImage)
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}