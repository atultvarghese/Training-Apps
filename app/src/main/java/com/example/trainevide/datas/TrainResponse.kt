package com.example.trainevide.datas

import com.google.gson.annotations.SerializedName

//{"train_num":12235,
//"name":"Dibrugarh - New Delhi Rajdhani Express",
//"train_from":"DBRG",
//"train_to":"NDLS",
//"data":{"id":"7974",
//"days":{"Fri":"0",
//"Mon":"0",
//"Sat":"0",
//"Sun":"0",
//"Thu":1,
//"Tue":"0",
//"Wed":"0"},
//"to_id":"664",
//"classes":["3A","2A","1A"],
//"from_id":"7288",
//"arriveTime":"13:55 +2 nights",
//"departTime":"19:25"}}

data class TrainResponse(
    @SerializedName("train_num") val trainNum: Int,
    val name: String,
    @SerializedName("train_from") val trainFrom: String,
    @SerializedName("train_to") val trainTo: String,
    val data: TrainData
)