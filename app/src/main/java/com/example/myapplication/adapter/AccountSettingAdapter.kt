package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.dataClass.AccountSettingModelClass
import com.example.myapplication.databinding.AccountSettingCardViewBinding

class AccountSettingAdapter(
    private val arrayList: ArrayList<AccountSettingModelClass>) :
    RecyclerView.Adapter<AccountSettingAdapter.ViewHolder>() {

    private lateinit var binding: AccountSettingCardViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = AccountSettingCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(binding: AccountSettingCardViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            with(binding) {
                tvSettingName.text = arrayList[position].settingName
                imgSetting.setImageResource(arrayList[position].accountSettingImage)
                if (position == arrayList.size - 1) {
                    btnArrow.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}