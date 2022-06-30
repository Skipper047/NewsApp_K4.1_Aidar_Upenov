package com.example.newsapp_k41_aidar_upenov.ui.Profile

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.newsapp_k41_aidar_upenov.R
import com.example.newsapp_k41_aidar_upenov.databinding.FragmentProfileBinding
import com.example.newsapp_k41_aidar_upenov.models.Prefs

class ProfileFragment : Fragment() {
    private lateinit var binding:FragmentProfileBinding
    private var change: Boolean = false

    private lateinit var text:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(LayoutInflater.from(requireContext()), container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivGallery.setOnClickListener {
            getContent.launch("image/*")
        }


        binding.editText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // РАБОТАЕТ ХОТЯ И НЕПРАВИЛЬНО, ПОЧЕМУ?
//                text = binding.editText.text.toString()
//                Prefs(requireContext()).saveText(text)
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                text = binding.editText.text.toString()
                Prefs(requireContext()).saveText(text)
            }

        })
        binding.editText.setText(Prefs(requireContext()).setText())



        // НЕ РАБОТАЕТ
//        if (findNavController().navigateUp()){
//            text = binding.editText.text.toString()
//            Prefs(requireContext()).saveText(text)
//        }
//        binding.editText.setText(Prefs(requireContext()).setText())

        // НЕПОНЯТНО ПОЧ НЕ РАБОТАЕТ
//        if (view.id == binding.editText.id){
//            text = binding.editText.text.toString()
//            Prefs(requireContext()).saveText(text)
//        }
//        binding.editText.setText(Prefs(requireContext()).setText())

//        binding.editText.setOnTouchListener()

//        ГОДНО НО НЕ РАБОТАЕТ
//        text = binding.editText.text.toString()
//        Prefs(requireContext()).saveText(text)
//        if (text == binding.editText.text.toString()){
//            Prefs(requireContext()).saveEdit()
//        }
//        if (Prefs(requireContext()).setEdit()){
//            binding.editText.setText(Prefs(requireContext()).setText())
//        }

//        ГОТОВОЕ РЕЩЕНИЕ С КНОПКОЙ
//       binding.btnSave.setOnClickListener {
//           text = binding.editText.text.toString()
//          Prefs(requireContext()).saveText(text)
//       }
//        binding.editText.setText(Prefs(requireContext()).setText())

            // ТОЖЕ НЕ РАБОТАЕТ, НО ЛОГИЧНЫЙ ВАРИАНТ
//            text = binding.editText.text.toString()
//        if (text == binding.editText.text.toString()){
//            change = true
//        }
//        if (change){
//            Prefs(requireContext()).saveText(text)
//        }
//        binding.editText.setText(Prefs(requireContext()).setText())


//        if (binding.editText.text.isNotEmpty()){
//            text = binding.editText.text.toString()
//            change = true
//        }
//        if (change){
//            Prefs(requireContext()).saveText(text)
//        }
//        binding.editText.setText(Prefs(requireContext()).setText())



        //        if (binding.editText.text != null){
//            text = binding.editText.text.toString()
//            Prefs(requireContext()).saveText(text)
//        }
//        binding.editText.setText(Prefs(requireContext()).setText())





    }

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()){
        uri: Uri? -> Glide.with(binding.ivGallery).load(uri).centerCrop().into(binding.ivGallery)
    }



}