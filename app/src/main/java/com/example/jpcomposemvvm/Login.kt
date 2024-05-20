package com.example.jpcomposemvvm

import LoginViewModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jpcomposemvvm.apis.LoginApi
import com.example.jpcomposemvvm.databinding.ActivityLoginBinding
import com.example.jpcomposemvvm.models.UserRepository
import retrofit2.Retrofit


class Login : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        val userRepository = UserRepository(LoginApi.retrofitBuilder)
        viewModel = ViewModelProvider(this, LoginViewModelFactory(userRepository)).get(LoginViewModel::class.java)

        // Example usage
        viewModel.login("eve.holt@reqres.in", "cityslicka") { result ->
            when (result) {
                is Result.Success -> {
                    val token = result.data.token
                    // Handle successful login
                }
                is Result.Error -> {
                    val errorMessage = result.message
                    // Handle error
                }
            }
        }
    }
}