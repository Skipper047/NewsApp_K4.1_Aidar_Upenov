package com.example.newsapp_k41_aidar_upenov.ui.Profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp_k41_aidar_upenov.R
import com.example.newsapp_k41_aidar_upenov.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding:FragmentProfileBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(LayoutInflater.from(requireContext()), container, false)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

}