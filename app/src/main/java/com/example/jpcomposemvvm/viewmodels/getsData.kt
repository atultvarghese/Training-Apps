package com.example.jpcomposemvvm.viewmodels

import androidx.lifecycle.LiveData
import com.example.jpcomposemvvm.models.Movies

interface getsData {
    fun onFetching()
    fun onSuccess(success : LiveData<ArrayList<Movies>>)
    fun onFailure(message : String)
}