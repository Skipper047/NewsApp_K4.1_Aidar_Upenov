package com.example.newsapp_k41_aidar_upenov.ui.home

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp_k41_aidar_upenov.R
import com.example.newsapp_k41_aidar_upenov.databinding.ItemNewsBinding
import com.example.newsapp_k41_aidar_upenov.models.News
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NewsAdapter(private val onClick: (position:Int)-> Unit): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private val list = arrayListOf<News>()
    private var boolean:Boolean = false


    var onItemLongClick: ((i:Int)-> Unit)?= null
//    var onItemLongClick: ((News)-> Unit)?= null

    inner class ViewHolder(private var binding: ItemNewsBinding):
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnLongClickListener {
                onItemLongClick?.invoke(adapterPosition)  // ПОЧЕМУ ПЕРЕЧЕРКНУТА КОГДА (POSITION)
                true
            }

        }
//        init {
//            itemView.setOnLongClickListener {
//                onItemLongClick?.invoke(list[adapterPosition])
//                true
//            }
        fun bind(news: News) {
            binding.textTitle.text = news.title
//            if (boolean){
//                binding.textChange.text = convertingToTime(news.createdAt)
//            }else{
//                binding.textLong.text = convertingToTime(news.createdAt)
//            }
            binding.textLong.text = convertingToTime(news.createdAt)

            if (adapterPosition%2==0){
                binding.itemNews.setBackgroundColor(Color.BLACK)
                binding.textTitle.setTextColor(Color.WHITE)
                binding.textLong.setTextColor(Color.WHITE)
//                binding.textChange.setTextColor(Color.WHITE)
            }
        }
        private fun convertingToTime(time: Long): String {
            val date = Date(time)
            val format = SimpleDateFormat("HH:mm, dd MMM yyyy")
            return format.format(date)

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onClick(position)
        }



    }
    override fun getItemCount()= list.size
    fun addItem(news: News) {
        list.add(0,news)
        notifyItemInserted(list.indexOf(news))

    }

    fun getItem(pos: Int): News {
        return list[pos]

    }

    fun addItems(list: List<News>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun replaceItem(news: News,poss: Int) {
//        list.set(poss,news)
        list[poss] = news
        notifyItemChanged(poss)
//        boolean =true
    }

    fun deleteItem(poss: Int) {
        list.removeAt(poss)
        notifyItemRemoved(poss)

    }



}