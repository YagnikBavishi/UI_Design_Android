package com.example.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.myapplication.R
import com.example.myapplication.commonFunctions.showToast
import com.example.myapplication.databinding.ActivityPhoneNumberBinding

class PhoneNumberActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhoneNumberBinding
    private val arrayList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.customToolbar.tvTitleScreen.text = getString(R.string.title_sign_in)
        addItemToArrayList()

        val arrayAdapter = ArrayAdapter(this, R.layout.spinner_list, arrayList)
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list)

        with(binding) {
            customView.spinner.adapter = arrayAdapter
            customView.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {}
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }

        binding.btnContinueWithNumber.setOnClickListener {
            val mobileNumber = binding.customView.etMobileNumber.text.toString().trim()
            when {
                mobileNumber.isEmpty() -> {
                    showToast(getString(R.string.et_mobile_number_empty))
                }
                mobileNumber.length < 10 -> {
                    showToast(getString(R.string.enter_vaild_number))
                }
                else -> {
                    startActivity(Intent(this, OtpActivity::class.java))
                }
            }
        }

        binding.customToolbar.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun addItemToArrayList() {
        arrayList.add(getString(R.string.number_1))
        arrayList.add(getString(R.string.number_2))
        arrayList.add(getString(R.string.number_3))
        arrayList.add(getString(R.string.number_4))
        arrayList.add(getString(R.string.number_5))
    }
}