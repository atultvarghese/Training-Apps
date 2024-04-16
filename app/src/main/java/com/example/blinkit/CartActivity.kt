package com.example.blinkit

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.blinkit.adapters.CartAdapter
import com.example.blinkit.adapters.ProductAdapter
import com.example.blinkit.apis.APIClient
import com.example.blinkit.databinding.ActivityCartBinding
import com.example.blinkit.databinding.ActivityMainBinding
import com.example.blinkit.databinding.CartItemLayoutBinding
import com.example.blinkit.datas.CartItem
import com.example.blinkit.datas.Products
import com.example.blinkit.dbconnection.UsersDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class CartActivity : AppCompatActivity() {

    lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCartBinding.inflate(layoutInflater) // Inflate the layout using View Binding
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var recycleView = binding.cartRecycleView
        var db : UsersDatabase = UsersDatabase.getDataBase(this)


        recycleView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycleView.layoutManager = StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
        var myApiData = ArrayList<CartItem>()
        myApiData.add(CartItem(1, "Atul", "https://via.placeholder.com/600/92c952", 2 ))
        var recycleViewAdapter = CartAdapter(myApiData)
        recycleView.adapter = recycleViewAdapter

        var h = Handler()

        thread {
            myApiData.clear()
            myApiData.clear()
            db.cartDbCreate().getCartData().forEach(){
                myApiData.add(CartItem(id = it.id,
                    title= it.title,
                    url = it.url,
                    qty = it.qty))
            }
            h.post{
                recycleViewAdapter.notifyDataSetChanged()
            }
        }
//
//        var apiCall = APIClient.retrofitBuilder.getData()
//        apiCall.enqueue(object : Callback<List<Products>> {
//            override fun onResponse(
//                call: Call<List<Products>>?,
//                response: Response<List<Products>>?
//            ) {
//                var products: List<Products>? = response?.body()
//                Log.i("products", "$products")
//                if (products != null){
//                    myApiData.addAll(products)
//                    recycleViewAdapter.notifyDataSetChanged()
//                    progressBar.visibility = View.INVISIBLE
//                }
//            }
//
//            override fun onFailure(call: Call<List<Products>>, t: Throwable) {
//                Log.i("api_error", "Error in api ${t.toString()}")
//                Toast.makeText(applicationContext,"Error in getting data from API, Check Your Internet!",
//                    Toast.LENGTH_LONG).show();
//            }
//        })

    }
}