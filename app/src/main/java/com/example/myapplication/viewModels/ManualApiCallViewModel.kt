package com.example.myapplication.viewModels

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.`interface`.ApiCallbackListener
import org.json.JSONObject

class ManualApiCallViewModel : BaseViewModel(), ApiCallbackListener {

    var validate = MutableLiveData<Boolean>()
    var message = MutableLiveData<String>()

    fun <T : Any> loginApiCall(
        apiUrl: String,
        method: String,
        body: JSONObject? = null,
        dataClass: Class<T>,
        requireResponseCode: Int
    ) {
        apiCall(apiUrl, method, body, dataClass, requireResponseCode, this)
    }

    override fun <T : Any> onSuccessful(data: T) {
        validate.postValue(true)
        message.postValue(data.toString())
    }

    override fun onUnsuccessful(error: String) {
        validate.postValue(false)
        message.postValue(error)
    }
}
