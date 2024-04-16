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
import com.example.blinkit.dbconnection.CartEntity
import com.example.blinkit.dbconnection.UsersDatabase
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface AdapterItemClickListener {
    fun onItemClicked(itemData :Products)
}
class ProductAdapter(private val listener: AdapterItemClickListener, var productList : ArrayList<Products>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var qty = 0
        var titleBox = itemView.findViewById<TextView>(R.id.title)
        var imageBox = itemView.findViewById<ImageView>(R.id.card_image)
        var plusButton = itemView.findViewById<Button>(R.id.plus)
        var minusButton = itemView.findViewById<Button>(R.id.minus)
        var addToCart = itemView.findViewById<Button>(R.id.addtocart)
        var qtyText = itemView.findViewById<TextView>(R.id.qty)
        var db : UsersDatabase = UsersDatabase.getDataBase(itemView.context)

        fun productBindData(products: Products){

            titleBox.text = products.title
            Picasso.with(imageBox.context)
                .load(products.url)
                .into(imageBox);

            plusButton.setOnClickListener(){
                if (qty < 10){
                    qty ++
                }
                qtyText.text = qty.toString()
            }

            minusButton.setOnClickListener(){
                if (qty > 0){
                    qty --
                }
                qtyText.text = qty.toString()
            }
            addToCart.setOnClickListener(){
                GlobalScope.launch { var cartItem = CartEntity()
                    cartItem.id = products.id.toInt()
                    cartItem.user = "Atul"
                    cartItem.title = products.title.toString()
                    cartItem.url = products.url.toString()
                    cartItem.qty = qty.toInt()
                    db.cartDbCreate().addToCart(cartItem) }

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