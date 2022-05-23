package com.example.myapplication.`interface`

import com.example.myapplication.dataClass.LoginRequest
import com.example.myapplication.dataClass.SignInModelClass
import com.example.myapplication.dataClass.SignUpModelClass
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("api/login")
    fun verifyLogin(@Body loginRequest: LoginRequest): Call<SignInModelClass>

    @POST("api/register")
    fun registerUser(@Body loginRequest: LoginRequest): Call<SignUpModelClass>

    companion object {
        fun getRetrofitObject(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}