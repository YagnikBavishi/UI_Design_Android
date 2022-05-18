package com.example.myapplication.`interface`

interface ApiCallbackListener {
    fun <T: Any> onSuccessful(data: T)
    fun onUnsuccessful(error: String)
}