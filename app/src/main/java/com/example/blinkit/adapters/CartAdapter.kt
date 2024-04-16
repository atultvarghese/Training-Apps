package com.example.blinkit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blinkit.R
import com.example.blinkit.datas.CartItem
import com.squareup.picasso.Picasso

class CartAdapter(var cartList : ArrayList<CartItem>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var titleBox = itemView.findViewById<TextView>(R.id.cart_title)
        var imageBox = itemView.findViewById<ImageView>(R.id.cart_card_image)
        var qtyText = itemView.findViewById<TextView>(R.id.cart_qty)

        fun cartBinder(carts : CartItem){
            titleBox.text = carts.title
            Picasso.with(imageBox.context)
                .load(carts.url)
                .into(imageBox);
            qtyText.text = carts.qty.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        var myView = LayoutInflater.from(parent.context).inflate(R.layout.cart_item_layout, parent, false)
        return CartAdapter.CartViewHolder(myView)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.cartBinder(cartList[position])
    }
}