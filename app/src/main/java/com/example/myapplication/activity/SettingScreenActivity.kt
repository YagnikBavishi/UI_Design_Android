package com.example.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.AccountSettingAdapter
import com.example.myapplication.adapter.SettingMoreOptionsAdapter
import com.example.myapplication.adapter.SettingMoreOptionsAdapter.Companion.VIEW_TYPE_ONE
import com.example.myapplication.adapter.SettingMoreOptionsAdapter.Companion.VIEW_TYPE_TWO
import com.example.myapplication.dataClass.AccountSettingModelClass
import com.example.myapplication.dataClass.SettingMoreOptionsModelClass
import com.example.myapplication.databinding.ActivitySettingScreenBinding

class SettingScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingScreenBinding
    private var accountSettingData = ArrayList<AccountSettingModelClass>()
    private var moreOptionsData = ArrayList<SettingMoreOptionsModelClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            customToolbar.tvTitleScreen.text = getString(R.string.title_setting)
            accountSettingRecyclerView.layoutManager = LinearLayoutManager(this@SettingScreenActivity)
            moreOptionsRecyclerView.layoutManager = LinearLayoutManager(this@SettingScreenActivity)
        }

        addDataToAccountSetting()
        addDataToMoreAccountSetting()

        with(binding) {
            accountSettingRecyclerView.adapter = AccountSettingAdapter(accountSettingData)
            moreOptionsRecyclerView.adapter = SettingMoreOptionsAdapter(moreOptionsData)
            customToolbar.btnBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun addDataToMoreAccountSetting() {
        moreOptionsData.apply {
            add(
                SettingMoreOptionsModelClass(
                    R.drawable.ic_networking,
                    getString(R.string.tv_news_letter),
                    VIEW_TYPE_ONE,
                    ""
                )
            )
            add(
                SettingMoreOptionsModelClass(
                    R.drawable.ic_email_icon,
                    getString(R.string.tv_text_message),
                    VIEW_TYPE_ONE,
                    ""
                )
            )
            add(
                SettingMoreOptionsModelClass(
                    R.drawable.ic_telephone,
                    getString(R.string.tv_phone_call),
                    VIEW_TYPE_ONE,
                    ""
                )
            )
            add(
                SettingMoreOptionsModelClass(
                    R.drawable.ic_currency_icon,
                    getString(R.string.tv_currency),
                    VIEW_TYPE_TWO,
                    getString(R.string.tv_usd)
                )
            )
            add(
                SettingMoreOptionsModelClass(
                    R.drawable.ic_language,
                    getString(R.string.tv_language),
                    VIEW_TYPE_TWO,
                    getString(R.string.tv_english)
                )
            )
            add(
                SettingMoreOptionsModelClass(
                    R.drawable.ic_link_icon,
                    getString(R.string.tv_link_accounts),
                    VIEW_TYPE_TWO,
                    getString(R.string.tv_facebook_google)
                )
            )
        }
    }

    private fun addDataToAccountSetting() {
        accountSettingData.apply {
            add(
                AccountSettingModelClass(
                    R.drawable.ic_lock_icon,
                    getString(R.string.tv_change_password)
                )
            )
            add(
                AccountSettingModelClass(
                    R.drawable.ic_notification_setting_icon,
                    getString(R.string.tv_order_management)
                )
            )
            add(
                AccountSettingModelClass(
                    R.drawable.ic_setting_icon,
                    getString(R.string.tv_document_management)
                )
            )
            add(
                AccountSettingModelClass(
                    R.drawable.ic_payment_icon,
                    getString(R.string.tv_payment)
                )
            )
            add(
                AccountSettingModelClass(
                    R.drawable.ic_sign_out_icon,
                    getString(R.string.tv_sign_out)
                )
            )
        }
    }
}