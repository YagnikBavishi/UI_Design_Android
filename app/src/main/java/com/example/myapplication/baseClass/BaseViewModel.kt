package com.example.myapplication.baseClass

import androidx.lifecycle.ViewModel
import com.example.myapplication.`interface`.ApiCallbackListener
import com.example.myapplication.constants.Constants
import com.example.myapplication.dataClass.ErrorModelClass
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

open class BaseViewModel : ViewModel() {

    fun <T : Any> apiCallWithRetrofit(
        apiCall: Call<T>,
        requireResponseCode: Int,
        apiInterfaceCallBack: ApiCallbackListener,
        ) {
        apiCall.enqueue(object : retrofit2.Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) =
                when (response.code()) {
                    requireResponseCode -> {
                        apiInterfaceCallBack.onSuccessful(response.body().toString())
                    }
                    Constants.FAILURE_CODE -> {
                        try {
                            val responseBody = response.errorBody()?.string()
                            val error = Gson().fromJson(responseBody, ErrorModelClass::class.java)
                            apiInterfaceCallBack.onUnsuccessful(error.error)
                        } catch (exception: IOException) {
                            exception.printStackTrace()
                        }
                    }
                    else -> {
                        apiInterfaceCallBack.onUnsuccessful(Constants.USER_NOT_VALID)
                    }
                }

            override fun onFailure(call: Call<T>, throwable: Throwable) =
                apiInterfaceCallBack.onUnsuccessful(throwable.toString())
        })
    }
}