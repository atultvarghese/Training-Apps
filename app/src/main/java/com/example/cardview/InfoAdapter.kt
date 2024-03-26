package com.example.cardview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InfoAdapter(var infoList : ArrayList<InformationData>) : RecyclerView.Adapter<InfoAdapter.InfoViewHolder>() {

    class InfoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var nameItem = itemView.findViewById<TextView>(R.id.textViewName)
        var place = itemView.findViewById<TextView>(R.id.textViewPlace)

        fun infoBindData(infodata : InformationData){
            nameItem.text = infodata.name
            place.text = infodata.email

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        var MyView = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return InfoViewHolder(MyView)

    }

    override fun getItemCount(): Int {
        return infoList.size
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.infoBindData(infoList[position])
    }
}