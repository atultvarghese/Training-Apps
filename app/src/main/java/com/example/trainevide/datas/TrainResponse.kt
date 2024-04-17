package com.example.trainevide.datas

import com.google.gson.annotations.SerializedName

data class TrainResponse(
    @SerializedName("train_num") val trainNum: Int,
    val name: String,
    @SerializedName("train_from") val trainFrom: String,
    @SerializedName("train_to") val trainTo: String,
    val data: TrainData
)