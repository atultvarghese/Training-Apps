package com.atultvarghese.trainevide

import APIClient
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.atultvarghese.trainevide.adapters.TrainsAdapter
import com.atultvarghese.trainevide.datas.TrainResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okio.IOException
import java.lang.reflect.Type

class TrainsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_trains)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var trainName = intent.extras!!.getString("trainName")
        var trainNumber = intent.extras!!.getString("trainNumber")
        var search = ""
        if (!trainName.isNullOrEmpty()){
            search = trainName
        } else if (!trainNumber.isNullOrEmpty()){
            search = trainNumber
        } else{
            Toast.makeText(this, "Both the fields are empty check your inputs", Toast.LENGTH_LONG).show()
            finish()
        }

        var recycleView = findViewById<RecyclerView>(R.id.recycleViewTrain)

        recycleView.layoutManager = StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
        var myApiData = ArrayList<TrainResponse>()

        var recycleViewAdapter = TrainsAdapter(this, myApiData)
        recycleView.adapter = recycleViewAdapter

        val apiClient = APIClient.create()

        apiClient.search(search, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                runOnUiThread {
                    Log.i("my_tag", "Error ${e.printStackTrace()}")
                    Toast.makeText(this@TrainsActivity, "Error in api ${e.printStackTrace()}.", Toast.LENGTH_LONG).show()
                    finish()
                }
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call, response: Response) {
                try {
                    val responseJsonString = response.body?.string()
                    Log.i("my_tag", "$responseJsonString")
                    val listType: Type = object : TypeToken<List<TrainResponse>>() {}.type
                    val gson = Gson()
                    val trainResponseList: List<TrainResponse> =
                        gson.fromJson(responseJsonString, listType)
//                Log.i("my_tag", "$trainResponseList")
                    // Perform UI updates on the main thread
                    runOnUiThread {
                        if (trainResponseList.isEmpty()) {
                            Toast.makeText(
                                this@TrainsActivity,
                                "No data found for the train.",
                                Toast.LENGTH_LONG
                            ).show()
                            finish()
                        } else {
                            myApiData.addAll(trainResponseList)
                            // Notify RecyclerView adapter of data changes
                            recycleViewAdapter.notifyDataSetChanged()
                        }
                    }
                } catch (e : Exception){
                    runOnUiThread {
                        Log.i("my_tag", "Error ${e.printStackTrace()}")
                        Toast.makeText(this@TrainsActivity, "Error ${e.printStackTrace()}.", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })

    }
}