package com.example.jpcomposemvvm.apis

import com.example.jpcomposemvvm.models.LoginUser
import com.example.jpcomposemvvm.models.Result
import com.example.jpcomposemvvm.models.data.LoginRequest
import com.example.jpcomposemvvm.models.data.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginService{
    @POST("login") // Adjust the endpoint accordingly
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}

class LoginApi {
    companion object{
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://reqres.in/api")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginService::class.java)
    }
}