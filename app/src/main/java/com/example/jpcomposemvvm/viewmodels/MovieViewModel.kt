package com.example.jpcomposemvvm.viewmodels

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jpcomposemvvm.apis.ApiClient
import com.example.jpcomposemvvm.models.Movies
import com.example.jpcomposemvvm.models.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    private var movieLiveData = MutableLiveData<List<Result<Any?>>>()
    fun getPopularMovies() {
        ApiClient.RetrofitInstance.api.getPopularMovies("69d66957eebff9666ea46bd464773cf0").enqueue(object :
            Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.body()!=null){
                    movieLiveData.value = response.body()!!.results
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }
    fun observeMovieLiveData() : LiveData<List<Result<Any?>>> {
        return movieLiveData
    }

    fun empty(view : View)  {
        Log.i("my_tag", "This is from empty")
        Toast.makeText(view.context, "This is empty", Toast.LENGTH_LONG).show()
        movieLiveData.value = arrayListOf()
    }
}
