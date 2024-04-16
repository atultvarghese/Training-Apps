package com.example.blinkit.adapters

import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.blinkit.R
import com.example.blinkit.datas.Products
import com.squareup.picasso.Picasso
interface AdapterItemClickListener {
    fun onItemClicked(itemData :Products)
}
class ProductAdapter(private val listener: AdapterItemClickListener, var productList : ArrayList<Products>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var titleBox = itemView.findViewById<TextView>(R.id.title)
        var imageBox = itemView.findViewById<ImageView>(R.id.card_image)
        var addButton = itemView.findViewById<Button>(R.id.plus)

        fun productBindData(products: Products){

            titleBox.text = products.title
            Picasso.with(imageBox.context)
                .load(products.url)
                .into(imageBox);

            addButton.setOnClickListener(){
                var cartSP : SharedPreferences = itemView.context.getSharedPreferences("cart",
                    AppCompatActivity.MODE_PRIVATE
                )
                var editor = cartSP.edit()
                editor.putInt("id", products.id)
                editor.putString("products", "value")
                editor.putString("key2", "value")
                Log.i("cart-data-adding", "Data Added to Cart $products")
                editor.apply()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var myView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ProductViewHolder(myView)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.productBindData(productList[position])
        holder.itemView.setOnClickListener {
            listener.onItemClicked(productList[position])
        }
    }

}