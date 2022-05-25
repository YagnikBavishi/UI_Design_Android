package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.dataClass.OnBoardingModelClass
import com.example.myapplication.databinding.OnboardingCardViewBinding

class OnBoardingScreenAdapter(private val arrayList: ArrayList<OnBoardingModelClass>) :
    RecyclerView.Adapter<OnBoardingScreenAdapter.ViewHolder>() {

    private lateinit var binding: OnboardingCardViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = OnboardingCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(binding: OnboardingCardViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            binding.onBoardingImage.setImageResource(arrayList[position].image)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}