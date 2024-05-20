package com.example.jpcomposemvvm.models

import com.example.jpcomposemvvm.apis.LoginService
import com.example.jpcomposemvvm.models.data.LoginRequest
import com.example.jpcomposemvvm.models.data.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(private val apiService: LoginService) {

    fun login(email: String, password: String, onResult: (Result<LoginResponse>) -> Unit) {
        val loginRequest = LoginRequest(email, password)
        val call = apiService.login(loginRequest)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    loginResponse?.let {
                        onResult(Result.Success(it))
                    } ?: onResult(Result.Error("Empty response body"))
                } else {
                    onResult(Result.Error("Error: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onResult(Result.Error("Network error: ${t.message}"))
            }
        })
    }
}
