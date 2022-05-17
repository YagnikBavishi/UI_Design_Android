package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.commonFunctions.showToast
import com.example.myapplication.databinding.ActivityOtpBinding


class OtpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.customToolbar.tvTitleScreen.text = getString(R.string.otp_title)

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) { }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(this@OtpActivity, R.color.splash_screen_background_color)
                ds.isUnderlineText = false
                ds.isFakeBoldText = true
            }
        }

        binding.customToolbar.btnBack.setOnClickListener {
            finish()
        }

        binding.btnVerifyNow.setOnClickListener {
            val etOtpOne = binding.etFirstNumber.text.toString().trim()
            val etOtpTwo = binding.etSecondNumber.text.toString().trim()
            val etOtpThree = binding.etThirdNumber.text.toString().trim()
            val etOtpFour = binding.etForthNumber.text.toString().trim()
            if (validation(etOtpOne, etOtpTwo, etOtpThree, etOtpFour)) {
                startActivity(Intent(this, HomeScreenActivity::class.java))
                finish()
            }
        }

        val spannable = SpannableString(binding.tvResendCode.text.toString())
        spannable.setSpan(clickableSpan, 23, 34, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        with(binding) {
            tvResendCode.text = spannable
            tvResendCode.movementMethod = LinkMovementMethod.getInstance()
        }

        val editTextArray = arrayOf<EditText>(binding.etFirstNumber, binding.etSecondNumber, binding.etThirdNumber, binding.etForthNumber)

        with(binding) {
            etFirstNumber.addTextChangedListener(GenericTextWatcher(binding.etFirstNumber, editTextArray))
            etSecondNumber.addTextChangedListener(GenericTextWatcher(binding.etSecondNumber, editTextArray))
            etThirdNumber.addTextChangedListener(GenericTextWatcher(binding.etThirdNumber, editTextArray))
            etForthNumber.addTextChangedListener(GenericTextWatcher(binding.etForthNumber, editTextArray))
        }
    }

    private fun validation(etOtpOne: String, etOtpTwo: String, etOtpThree: String, etOtpFour: String): Boolean {
        return when {
            etOtpOne.isEmpty() -> {
                showToast(getString(R.string.et_empty_first))
                false
            }
            etOtpTwo.isEmpty() -> {
                showToast(getString(R.string.et_empty_second))
                Toast.makeText(this, getString(R.string.et_empty_second), Toast.LENGTH_SHORT).show()
                false
            }
            etOtpThree.isEmpty() -> {
                showToast(getString(R.string.et_empty_third))
                false
            }
            etOtpFour.isEmpty() -> {
                showToast(getString(R.string.et_empty_forth))
                false
            }
            else -> {
                true
            }
        }
    }
}

