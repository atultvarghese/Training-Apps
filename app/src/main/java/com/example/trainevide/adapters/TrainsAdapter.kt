package com.example.trainevide.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.trainevide.R
import com.example.trainevide.databinding.TrainLayoutBinding
import com.example.trainevide.datas.TrainResponse
import com.google.android.material.button.MaterialButton

class TrainsAdapter(val context: Context, var trains : ArrayList<TrainResponse>) : RecyclerView.Adapter<TrainsAdapter.TrainsViewHolder>(){
    class TrainsViewHolder(var binding: TrainLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        private fun changeColour(train : TrainResponse, dayString: String, dayButton : MaterialButton){
            if (train.data.days.get(dayString).toString() == "1"){
                dayButton.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(binding.root.context, R.color.green))
            } else {
                dayButton.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(binding.root.context, R.color.light_red))
            }
        }
        @SuppressLint("SetTextI18n")
        fun trainBindData(train : TrainResponse){
            binding.trainNameTextView.text = train.name
            binding.trainNumberTextView.text = train.trainNum.toString()
            binding.fromToTextView.text = "Form ${train.trainFrom} To ${train.trainTo}"
            binding.departTimeTextView.text = "Departure on ${train.data.departTime}"
            binding.arriveTimeTextView.text = "Arrival in ${train.data.arriveTime}"
            changeColour(train, "Mon", binding.monButton)
            changeColour(train, "Tue", binding.tueButton)
            changeColour(train, "Wed", binding.wedButton)
            changeColour(train, "Thu", binding.thuButton)
            changeColour(train, "Fri", binding.friButton)
            changeColour(train, "Sat", binding.satButton)
            changeColour(train, "Sun", binding.sunButton)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainsViewHolder {

//        var trainView = LayoutInflater.from(parent.context).inflate(R.layout.train_layout, parent, false)
//        return TrainsAdapter.TrainsViewHolder(trainView)
        val binding = TrainLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrainsAdapter.TrainsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  trains.size
    }

    override fun onBindViewHolder(holder: TrainsViewHolder, position: Int) {
        holder.trainBindData(trains[position])
    }
}