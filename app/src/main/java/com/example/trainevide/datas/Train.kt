package com.example.trainevide.datas


data class TrainData(
    val id: String,
    val days: Map<String, Int>, // Changed from Map<String, String> to Map<String, Int>
    val to_id: String,
    val classes: Any, // Changed type to Any
    val from_id: String,
    val arriveTime: String,
    val departTime: String
)
