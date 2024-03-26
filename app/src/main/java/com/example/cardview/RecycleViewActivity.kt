package com.example.cardview

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecycleViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycle_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recycleView = findViewById<RecyclerView>(R.id.recycle_view)

        recycleView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
//        recycleView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
//        recycleView.layoutManager = GridLayoutManager(this,2, RecyclerView.HORIZONTAL, false)
//        recycleView.layoutManager = GridLayoutManager(this,2, RecyclerView.VERTICAL, false)

        var myInfoList = ArrayList<InformationData>()
//
        myInfoList.add(InformationData("Atul", "Kozhikode"))
        myInfoList.add(InformationData("Ajith", "Palakad"))
        myInfoList.add(InformationData("Swathi", "Tamil Nadu"))
        myInfoList.add(InformationData("Vijay", "Kochi"))
        myInfoList.add(InformationData("Next Atul", "Kozhikode"))
        myInfoList.add(InformationData("Next Ajith", "Palakad"))
        myInfoList.add(InformationData("Next Swathi is a big sentance and here is an example", "Tamil Nadu"))
        myInfoList.add(InformationData("Next Vijay", "Kochi"))
        myInfoList.add(InformationData("New Atul", "Kozhikode"))
        myInfoList.add(InformationData("New Ajith", "Palakad"))
        myInfoList.add(InformationData("New Swathi", "Tamil Nadu"))
        myInfoList.add(InformationData("New Vijay", "Kochi"))
        myInfoList.add(InformationData("Last Atul", "Kozhikode"))
        myInfoList.add(InformationData("Last Ajith", "Palakad"))
        myInfoList.add(InformationData("Last Swathi", "Tamil Nadu"))
        myInfoList.add(InformationData("Last Vijay", "Kochi"))
//
//        recycleView.adapter = InfoAdapter(myInfoList)
        var myAdaptor = InfoAdapter(myInfoList)
        recycleView.adapter = myAdaptor

        val makeCall = ApiClient.retrofitBuilder.getData()
        Log.i("apidata", "Its comming afer make call")

        makeCall.enqueue(object : Callback<List<InformationData>> {
            override fun onResponse(call: Call<List<InformationData>>?, response: Response<List<InformationData>>?) {
                val usersList: List<InformationData>? = response?.body()
                Log.i("apidata", "$usersList")
                if (usersList != null) {
                    myInfoList.clear() // Clear existing data
                    myInfoList.addAll(usersList) // Add new data
                    myAdaptor.notifyDataSetChanged() // Notify adapter about the changes
                }
            }

            override fun onFailure(call: Call<List<InformationData>>?, t: Throwable?) {
                Log.i("apidata", "Error is ${t.toString()}")
            }
        })
    }
}