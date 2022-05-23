package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.dataClass.SettingMoreOptionsModelClass
import com.example.myapplication.databinding.SettingAdvanceOptionsBinding
import com.example.myapplication.databinding.SettingMoreOptionsCardViewBinding

class SettingMoreOptionsAdapter(
    val arrayList: ArrayList<SettingMoreOptionsModelClass>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var settingMoreOptionBinding: SettingMoreOptionsCardViewBinding
    private lateinit var settingAdvanceOptionsBinding: SettingAdvanceOptionsBinding

    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == VIEW_TYPE_ONE) {
            settingMoreOptionBinding = SettingMoreOptionsCardViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ViewHolderOne(settingMoreOptionBinding)
        }
        settingAdvanceOptionsBinding =
            SettingAdvanceOptionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderTwo(settingAdvanceOptionsBinding)
    }

    override fun getItemViewType(position: Int): Int {
        return arrayList[position].viewType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (arrayList[position].viewType == VIEW_TYPE_ONE) {
            with(settingMoreOptionBinding) {
                imgMoreOptions.setImageResource(arrayList[position].moreOptionImage)
                tvMoreOptionsName.text = arrayList[position].moreOptionName
            }
        } else {
            with(settingAdvanceOptionsBinding) {
                tvMoreOptionsName.text = arrayList[position].moreOptionName
                imgMoreOptions.setImageResource(arrayList[position].moreOptionImage)
                currencyName.text = arrayList[position].moreOptionType
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolderOne(settingMoreOptionBinding: SettingMoreOptionsCardViewBinding) :
        RecyclerView.ViewHolder(settingMoreOptionBinding.root)

    inner class ViewHolderTwo(settingAdvanceOptionsBinding: SettingAdvanceOptionsBinding) :
        RecyclerView.ViewHolder(settingAdvanceOptionsBinding.root)
}