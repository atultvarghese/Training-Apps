package com.example.trainevide

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.trainevide.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.searchButton.setOnClickListener(){
            var trainIntent = Intent(context, TrainsActivity::class.java)
            trainIntent.putExtra("trainName", binding.trainName.text.toString())
            trainIntent.putExtra("trainNumber", binding.trainNumber.text.toString())
            startActivity(trainIntent)
        }
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}