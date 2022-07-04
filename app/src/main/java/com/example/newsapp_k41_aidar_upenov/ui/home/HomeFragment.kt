package com.example.newsapp_k41_aidar_upenov.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newsapp_k41_aidar_upenov.App
import com.example.newsapp_k41_aidar_upenov.R
import com.example.newsapp_k41_aidar_upenov.databinding.FragmentHomeBinding
import com.example.newsapp_k41_aidar_upenov.models.News

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var adapter: NewsAdapter
    private var bolean: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = NewsAdapter{
            val news = adapter.getItem(it)
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            val bundle = Bundle()
            bundle.putSerializable("news",news)
            // Почему передаётся текс а не только индекс?🤔
            bolean=true

            findNavController().navigate(R.id.newsFragment, bundle)
        }
        val list = App.database.newsDao().getAll()
        val reverseList = list.asReversed()
        adapter.addItems(reverseList)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener {
        findNavController().navigate(R.id.newsFragment)
        }
        parentFragmentManager.setFragmentResultListener("rk_news", viewLifecycleOwner){
            requestKey, bundle->
            val news = bundle.getSerializable("news") as News
            val poss: Int? = null
            if (bolean){
                poss?.let { adapter.replaceItem(news, it) }// как приходит индекс?😕
            }else{
                adapter.addItem(news)
            }


        }


        binding.recyclerView.adapter = adapter

        adapter.onItemLongClick={
            createAlertDialog(it)  //it.id первый вариант, когда передовался News
        }


    }

    private fun createAlertDialog(it:Int) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Вы уверены что хотите удалить?")
        builder.setMessage("Удаление News очистит все его данные")
        builder.setPositiveButton("Yes"){ _, _ ->
            val news = adapter.getItem(it)
            adapter.deleteItem(it)
            App.database.newsDao().delete(news)
            adapter.notifyDataSetChanged()

        }
        builder.setNegativeButton("NO"){_,_ ->

        }
        builder.setNeutralButton("Cancel"){_,_ ->

        }
        builder.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}