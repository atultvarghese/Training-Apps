package com.example.jpcomposemvvm.apis

import com.example.jpcomposemvvm.models.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("popular?")
    fun getPopularMovies(@Query("api_key") api_key : String) : Call<Movies>
}