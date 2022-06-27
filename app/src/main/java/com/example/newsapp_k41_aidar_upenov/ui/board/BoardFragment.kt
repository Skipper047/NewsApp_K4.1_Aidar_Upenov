package com.example.newsapp_k41_aidar_upenov.ui.board

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.newsapp_k41_aidar_upenov.databinding.FragmentBoardBinding
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator


class BoardFragment : Fragment() {

    private lateinit var binding: FragmentBoardBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBoardBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var adapter = BoardAdapter {
            findNavController().navigateUp()
        }
        binding.viewPager.adapter = adapter

        val dotsIndicator = binding.dotsIndicator
        val viewPager = binding.viewPager
        dotsIndicator.setViewPager2(viewPager)

//        binding.circleInd.setViewPager(binding.viewPager)
//        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//        val indicator :CircleIndicator3 = binding.circleInd
//        indicator.setViewPager(binding.viewPager)

        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (position == 2) {
                    binding.btnSkip.visibility = View.INVISIBLE
                } else {
                    binding.btnSkip.visibility = View.VISIBLE
                }
                // if(position !=2){ binding.btnSkip.visibility = View.VISIBLE } + visibility=INVISIBLE in xml file
                // второй вариант решения
            }
        })


        binding.btnSkip.setOnClickListener {
            findNavController().navigateUp()
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }

        })

    }


}