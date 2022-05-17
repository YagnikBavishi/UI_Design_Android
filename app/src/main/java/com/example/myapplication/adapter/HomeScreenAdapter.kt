package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.activity.HomeScreenActivity
import com.example.myapplication.dataClass.HomeScreenModelClass
import com.example.myapplication.databinding.HomeScreenCardViewBinding

class HomeScreenAdapter(
    private val arrayList: ArrayList<HomeScreenModelClass>,
    contextThis: HomeScreenActivity,
    spinnerData: ArrayList<String>
): RecyclerView.Adapter<HomeScreenAdapter.ViewHolder>() {

    private lateinit var binding: HomeScreenCardViewBinding
    private val arrayAdapter = ArrayAdapter(contextThis, R.layout.home_screen_spinner_data, spinnerData)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = HomeScreenCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(binding: HomeScreenCardViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            with(binding) {
                eventName.text = arrayList[position].eventName
                eventAddress.text = arrayList[position].eventAddress
                arrayAdapter.setDropDownViewResource(R.layout.home_screen_spinner_data)
                spinner.adapter = arrayAdapter
                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {}
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}