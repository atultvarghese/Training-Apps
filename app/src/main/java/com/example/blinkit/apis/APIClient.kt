package com.example.blinkit.apis

import retrofit2.Call
import com.example.blinkit.datas.Products
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
interface ApiService{
    @GET("photos")
    fun getData() : Call<List<Products>>
}

class APIClient {

    companion object {
        var retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    }


}