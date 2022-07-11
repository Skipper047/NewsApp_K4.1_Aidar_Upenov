package com.example.newsapp_k41_aidar_upenov.kotlinFile

import android.content.Context
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment

fun Fragment.sT(msg:String){
    Toast.makeText(requireContext(),msg,Toast.LENGTH_SHORT).show()
}