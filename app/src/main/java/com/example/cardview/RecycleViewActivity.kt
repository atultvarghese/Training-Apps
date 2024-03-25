package com.example.cardview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class RecycleViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycle_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recycleView = findViewById<RecyclerView>(R.id.recycle_view)

        recycleView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        var myInfoList = ArrayList<InformationData>()

        myInfoList.add(InformationData("Atul", "Kozhikode"))
        myInfoList.add(InformationData("Ajith", "Palakad"))
        myInfoList.add(InformationData("Swathi", "Tamil Nadu"))
        myInfoList.add(InformationData("Vijay", "Kochi"))
        myInfoList.add(InformationData("Next Atul", "Kozhikode"))
        myInfoList.add(InformationData("Next Ajith", "Palakad"))
        myInfoList.add(InformationData("Next Swathi", "Tamil Nadu"))
        myInfoList.add(InformationData("Next Vijay", "Kochi"))
        myInfoList.add(InformationData("New Atul", "Kozhikode"))
        myInfoList.add(InformationData("New Ajith", "Palakad"))
        myInfoList.add(InformationData("New Swathi", "Tamil Nadu"))
        myInfoList.add(InformationData("New Vijay", "Kochi"))
        myInfoList.add(InformationData("Last Atul", "Kozhikode"))
        myInfoList.add(InformationData("Last Ajith", "Palakad"))
        myInfoList.add(InformationData("Last Swathi", "Tamil Nadu"))
        myInfoList.add(InformationData("Last Vijay", "Kochi"))

        recycleView.adapter = InfoAdapter(myInfoList)

    }
}