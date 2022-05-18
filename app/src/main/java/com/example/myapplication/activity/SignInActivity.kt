package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.commonFunctions.showToast
import com.example.myapplication.constants.Constants
import com.example.myapplication.viewModels.ManualApiCallViewModel
import com.example.myapplication.dataClass.SignInModelClass
import com.example.myapplication.commonFunctions.spannableText
import com.example.myapplication.constants.Constants
import com.example.myapplication.databinding.ActivitySignInBinding
import org.json.JSONObject

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private val viewModel: ManualApiCallViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.customToolbar.tvTitleScreen.text = getString(R.string.title_sign_in)

        binding.progressBar.visibility = View.INVISIBLE

        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val credentials = JSONObject()
            credentials.put("email", email)
            credentials.put("password", password)

            if (validation(email, password)) {
                binding.progressBar.visibility = View.VISIBLE
                viewModel.loginApiCall(Constants.SIGN_IN_URL, Constants.POST, credentials, SignInModelClass::class.java, 200)
            }
        }

        setViewModelMessage()

        binding.customToolbar.btnBack.setOnClickListener {
            finish()
        }

        val spannableString = spannableText(Constants.TWENTY_TWO, Constants.THIRTY, R.color.splash_screen_background_color, binding.tvDoNotHaveAccount.text.toString(), this) {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.tvDoNotHaveAccount.text = spannableString
        binding.tvDoNotHaveAccount.movementMethod = LinkMovementMethod.getInstance()

    }

    private fun setViewModelMessage() {
        with(viewModel) {
            validate.observe(this@SignInActivity) {
                if (it) {
                    binding.progressBar.visibility = View.INVISIBLE
                    startActivity(Intent(this@SignInActivity, HomeScreenActivity::class.java))
                    finish()
                }
            }
            message.observe(this@SignInActivity) {
                binding.progressBar.visibility = View.INVISIBLE
                Toast.makeText(this@SignInActivity, it, Toast.LENGTH_SHORT).show()
            }
        }
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