package com.example.trainevide.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainevide.databinding.TrainLayoutBinding
import com.example.trainevide.datas.TrainResponse

class TrainsAdapter(var trains : ArrayList<TrainResponse>) : RecyclerView.Adapter<TrainsAdapter.TrainsViewHolder>(){
    class TrainsViewHolder(var binding: TrainLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun trainBindData(train : TrainResponse){
            binding.trainNameTextView.text = train.name
            binding.trainNumberTextView.text = train.name
            binding.fromToTextView.text = "${train.trainFrom}  to ${train.trainTo}"
            binding.arriveTimeTextView.text = train.data.arriveTime
            binding.departTimeTextView.text = train.data.departTime
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainsViewHolder {

//        var trainView = LayoutInflater.from(parent.context).inflate(R.layout.train_layout, parent, false)
//        return TrainsAdapter.TrainsViewHolder(trainView)
        var binding = TrainLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrainsAdapter.TrainsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  trains.size
    }

    override fun onBindViewHolder(holder: TrainsViewHolder, position: Int) {
        holder.trainBindData(trains[position])
    }
}