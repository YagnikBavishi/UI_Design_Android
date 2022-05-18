package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import com.example.myapplication.R
import com.example.myapplication.commonFunctions.showToast
import com.example.myapplication.commonFunctions.spannableText
import com.example.myapplication.constants.Constants
import com.example.myapplication.databinding.ActivitySignUpBinding
import com.example.myapplication.viewModels.ManualApiCallViewModel

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: ManualApiCallViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            customToolbar.tvTitleScreen.text = getString(R.string.title_sign_up)
            customToolbar.btnBack.setOnClickListener {
                finish()
            }
            progressBar.visibility = View.INVISIBLE
        }

        setViewModelMessage()

        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val fullName = binding.etFullName.text.toString().trim()
            val phoneNumber = binding.etPhoneNumber.text.toString().trim()
            val conformPassword = binding.etConformPassword.text.toString().trim()
            if (validation(email, password, fullName, phoneNumber, conformPassword)) {
                Toast.makeText(this, getString(R.string.successful_message), Toast.LENGTH_SHORT).show()
            }
        }
        val spannableString = spannableText(Constants.TWENTY_FIVE, Constants.THIRTY_FOUR, R.color.splash_screen_background_color, binding.tvDoNotHaveAccount.text.toString(), this) {
            finish()
        }

        binding.tvDoNotHaveAccount.text = spannableString
        binding.tvDoNotHaveAccount.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun setViewModelMessage() {
        with(viewModel) {
            validate.observe(this@SignUpActivity) {
                if (it) {
                    binding.progressBar.visibility = View.INVISIBLE
                    startActivity(Intent(this@SignUpActivity, HomeScreenActivity::class.java))
                    finish()
                }
            }
            message.observe(this@SignUpActivity) {
                binding.progressBar.visibility = View.INVISIBLE
                Toast.makeText(this@SignUpActivity, it, Toast.LENGTH_SHORT).show()
            }
        }
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
            fullName.length < 4 -> {
                showToast(getString(R.string.full_name_short))
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
                showToast(getString(R.string.password_empty))
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