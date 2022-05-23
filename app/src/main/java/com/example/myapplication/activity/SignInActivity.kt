package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.commonFunctions.showToast
import com.example.myapplication.constants.Constants
import com.example.myapplication.commonFunctions.spannableText
import com.example.myapplication.databinding.ActivitySignInBinding
import com.example.myapplication.viewModels.SignInViewModel

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            customToolbar.tvTitleScreen.text = getString(R.string.title_sign_in)
            progressBar.visibility = View.INVISIBLE
        }

        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            if (validationOfData(email, password)) {
                binding.progressBar.visibility = View.VISIBLE
                viewModel.signInWithRetrofit(email, password, Constants.SUCCESS_CODE)
            }
        }

        bindViewModelData()

        binding.customToolbar.btnBack.setOnClickListener {
            finish()
        }

        val spannableString = spannableText(Constants.TWENTY_TWO, Constants.THIRTY, R.color.splash_screen_background_color, binding.tvDoNotHaveAccount.text.toString(), this) {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.tvDoNotHaveAccount.text = spannableString
        binding.tvDoNotHaveAccount.movementMethod = LinkMovementMethod.getInstance()

    }

    private fun bindViewModelData() {
        with(viewModel) {
            validateResponse.observe(this@SignInActivity) {
                when {
                    it -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        startActivity(
                            Intent(
                                this@SignInActivity,
                                HomeScreenActivity::class.java
                            )
                        )
                        finish()
                    }
                    else -> {
                        errorMessage.observe(this@SignInActivity) { error ->
                            binding.progressBar.visibility = View.INVISIBLE
                            showToast(error)

                        }
                    }
                }
            }
            message.observe(this@SignInActivity) {
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
    }

    private fun validationOfData(email: String, password: String): Boolean {
        return when {
            email.isEmpty() -> {
                showToast(getString(R.string.empty_email))
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
            else -> {
                true
            }
        }
    }
}