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


data class TrainData(
    val id: String,
    val days: Map<String, Int>, // Changed from Map<String, String> to Map<String, Int>
    val to_id: String,
    val classes: Any, // Changed type to Any
    val from_id: String,
    val arriveTime: String,
    val departTime: String
)
