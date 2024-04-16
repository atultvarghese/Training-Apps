package com.example.blinkit

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.blinkit.adapters.AdapterItemClickListener
import com.example.blinkit.adapters.ProductAdapter
import com.example.blinkit.apis.APIClient
import com.example.blinkit.datas.Products
import com.example.blinkit.dbconnection.UsersDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class DashBoardActivity : AppCompatActivity(), AdapterItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dash_board)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recycleView = findViewById<RecyclerView>(R.id.recycle_view)
        val progressBar = findViewById<ProgressBar>(R.id.progress_rv)
        var goToCart = findViewById<Button>(R.id.gotocart)
        var db : UsersDatabase = UsersDatabase.getDataBase(this)

//        recycleView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycleView.layoutManager = StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
        var myApiData = ArrayList<Products>()
        var recycleViewAdapter = ProductAdapter(this, myApiData)
        recycleView.adapter = recycleViewAdapter

        var apiCall = APIClient.retrofitBuilder.getData()
        apiCall.enqueue(object : Callback<List<Products>>{
            override fun onResponse(
                call: Call<List<Products>>?,
                response: Response<List<Products>>?
            ) {
                var products: List<Products>? = response?.body()
                Log.i("products", "$products")
                if (products != null){
                    myApiData.addAll(products)
                    recycleViewAdapter.notifyDataSetChanged()
                    progressBar.visibility = View.INVISIBLE
                }
            }

            override fun onFailure(call: Call<List<Products>>, t: Throwable) {
                Log.i("api_error", "Error in api ${t.toString()}")
                Toast.makeText(applicationContext,"Error in getting data from API, Check Your Internet!",Toast.LENGTH_LONG).show();
            }
        })
//        var h = Handler()
//
//
//        goToCart.setOnClickListener(){
//            thread {
//                myApiData.clear()
//                db.cartDbCreate().getCartData().forEach(){
//                    myApiData.add(Products(id = it.id,
//                        title= it.title,
//                        url = it.url))
//                }
//                h.post{
//                    recycleViewAdapter.notifyDataSetChanged()
//                }
//            }
//
//        }

        goToCart.setOnClickListener(){
            startActivity(Intent(this, CartActivity::class.java))
        }

    }
    override fun onItemClicked(itemData : Products) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val fragment = CardFragment.newInstance(itemData)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.replace(android.R.id.content, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}