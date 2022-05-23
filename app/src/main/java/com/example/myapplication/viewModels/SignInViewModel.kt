package com.example.myapplication.viewModels

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.`interface`.ApiCallbackListener
import com.example.myapplication.`interface`.ApiInterface
import com.example.myapplication.baseClass.BaseViewModel
import com.example.myapplication.dataClass.LoginRequest

class SignInViewModel : BaseViewModel(), ApiCallbackListener {

    val validateResponse = MutableLiveData<Boolean>()
    val message = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()
    private val apiInterface: ApiInterface = ApiInterface.getRetrofitObject().create(ApiInterface::class.java)

     fun signInWithRetrofit(
        email: String,
        password: String,
        requireRequestCode: Int,
    ) {
         val loginRequest = LoginRequest(email, password)
         val signInApi = apiInterface.verifyLogin(loginRequest)
         apiCallWithRetrofit(signInApi, requireRequestCode, this@SignInViewModel)
    }

    fun signUpWithRetrofit(
        email: String,
        password: String,
        requireRequestCode: Int,
    ) {
        val loginRequest = LoginRequest(email, password)
        val signUpApi = apiInterface.registerUser(loginRequest)
        apiCallWithRetrofit(signUpApi, requireRequestCode, this)
    }

    override fun <T : Any> onSuccessful(data: T) {
        validateResponse.postValue(true)
        message.postValue(data.toString())
    }

    override fun onUnsuccessful(error: String) {
        validateResponse.postValue(false)
        errorMessage.postValue(error)
    }
}
