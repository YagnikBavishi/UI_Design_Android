package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.commonFunctions.showToast
import com.example.myapplication.commonFunctions.spannableText
import com.example.myapplication.constants.Constants
import com.example.myapplication.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.customToolbar.tvTitleScreen.text = getString(R.string.title_sign_in)

        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            if (validation(email, password)) {
                Toast.makeText(this, getString(R.string.successful_message), Toast.LENGTH_SHORT).show()
            }
        }

        binding.customToolbar.btnBack.setOnClickListener {
            finish()
        }

        val spannableString = spannableText(Constants.START_INDEX_TWENTY_TWO, Constants.END_INDEX_THIRTY, R.color.splash_screen_background_color, binding.tvDoNotHaveAccount.text.toString(), this) {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.tvDoNotHaveAccount.text = spannableString
        binding.tvDoNotHaveAccount.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun validation(email: String, password: String): Boolean {
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