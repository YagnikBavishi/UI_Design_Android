package com.example.myapplication.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.`interface`.ApiCallbackListener
import com.example.myapplication.dataClass.ErrorModelClass
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

open class BaseViewModel : ViewModel() {

    fun <T : Any> apiCall(
        apiUrl: String,
        method: String,
        body: JSONObject? = null,
        dataClass: Class<T>,
        requireResponseCode: Int,
        apiInterface: ApiCallbackListener,
    ) {
        viewModelScope.launch(Dispatchers.IO){
            with(URL(apiUrl).openConnection() as HttpURLConnection) {
                requestMethod = method
                setRequestProperty("Content-Type", "application/json")

                body?.let {
                    outputStream.bufferedWriter().use {
                        it.write(body.toString())
                    }
                }
                val response = StringBuffer()
                when (responseCode) {
                    requireResponseCode -> {
                        BufferedReader(InputStreamReader(inputStream)).use {
                            var inputLine = it.readLine()
                            while (inputLine != null) {
                                response.append(inputLine)
                                inputLine = it.readLine()
                            }
                        }
                        val jsonResponse = Gson().fromJson(response.toString(), dataClass)
                        apiInterface.onSuccessful(jsonResponse)
                    }
                    in 400..499 -> {
                        BufferedReader(InputStreamReader(errorStream)).use {
                            var inputLine = it.readLine()
                            while (inputLine != null) {
                                response.append(inputLine)
                                inputLine = it.readLine()
                            }
                        }
                        val jsonResponse =
                            Gson().fromJson(response.toString(), ErrorModelClass::class.java)
                        apiInterface.onUnsuccessful(jsonResponse.error)
                    }
                    else -> {
                        apiInterface.onUnsuccessful(responseCode.toString())
                    }
                }
            }
        }
    }
}