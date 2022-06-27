package com.example.newsapp_k41_aidar_upenov.ui.board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp_k41_aidar_upenov.databinding.PagerBoardBinding
import com.example.newsapp_k41_aidar_upenov.models.Boards

class BoardAdapter (private val onClickStart: ()-> Unit): RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

//    private val title = arrayListOf("Salam","Privet","Hello")
    private val listBoard = arrayListOf<Boards>(Boards("Lesson 1","https://w7.pngwing.com/pngs/898/996/png-transparent-internet-meme-computer-meme-angle-white-child.png",
        "Open gallery"),
        Boards("Lesson 2","https://www.meme-arsenal.com/memes/f3c9a247bdea140ee8727fdaf1299f6e.jpg","Edit text"),Boards("Lesson 3","https://www.voicy.network/Content/Clips/Images/5ed33cf9-2ec3-433a-950b-77bb9e74838e-small.png",
            "EMOTIONAL DAMAGE"))


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PagerBoardBinding.inflate
            (LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listBoard[position])

    }

    override fun getItemCount()= listBoard.size



    inner class ViewHolder(private var binding: PagerBoardBinding)
        :RecyclerView.ViewHolder(binding.root) {
        fun bind(boards: Boards) {
            binding.textTitle.text = boards.title
            binding.textDesc.text = boards.Description
            Glide.with(binding.ImageView).load(boards.Image).into(binding.ImageView)


            if (position != listBoard.size-1){
                binding.btnStart.visibility = View.INVISIBLE
            }
//            else {
//                binding.btnStart.visibility = View.INVISIBLE
//            }
            binding.btnStart.setOnClickListener {
                onClickStart()
            }

        }

    }
}