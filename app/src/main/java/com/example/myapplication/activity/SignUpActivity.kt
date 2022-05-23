package com.example.myapplication.activity

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.commonFunctions.showToast
import com.example.myapplication.commonFunctions.spannableText
import com.example.myapplication.constants.Constants
import com.example.myapplication.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.customToolbar.tvTitleScreen.text = getString(R.string.title_sign_up)

        binding.customToolbar.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val fullName = binding.etFullName.text.toString().trim()
            val phoneNumber = binding.etPhoneNumber.text.toString().trim()
            val conformPassword  = binding.etConformPassword.text.toString().trim()
            if (validation(email, password, fullName, phoneNumber, conformPassword)) {
                Toast.makeText(this, getString(R.string.successful_message), Toast.LENGTH_SHORT).show()
            }
        }
        val spannableString = spannableText(Constants.START_INDEX_TWENTY_FIVE, Constants.END_INDEX_THIRTY_FOUR, R.color.splash_screen_background_color, binding.tvDoNotHaveAccount.text.toString(), this) {
            finish()
        }

        binding.tvDoNotHaveAccount.text = spannableString
        binding.tvDoNotHaveAccount.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun validation(
        email: String,
        password: String,
        fullName: String,
        phoneNumber: String,
        conformPassword: String
    ): Boolean {
        return when {
            fullName.isEmpty() -> {
                showToast(getString(R.string.full_name_empty))
                false
            }
            phoneNumber.length != 10 -> {
                showToast(getString(R.string.enter_vaild_number))
                false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                showToast(getString(R.string.email_not_match))
                false
            }
            password.isEmpty() -> {
                showToast(getString(R.string.password_empty))
                false
            }
            password.length < 4 -> {
                showToast(getString(R.string.password_not_match))
                false
            }
            conformPassword.isEmpty() -> {
                showToast(getString(R.string.tv_conform_password_empty))
                false
            }
            conformPassword != password -> {
                showToast(getString(R.string.password_and_conform_password_not_match))
                false
            }
            else -> {
                true
            }
        }
    }
}