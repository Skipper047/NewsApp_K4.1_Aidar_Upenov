package com.example.newsapp_k41_aidar_upenov.ui.home

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp_k41_aidar_upenov.databinding.ItemNewsBinding
import com.example.newsapp_k41_aidar_upenov.models.News
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(private val onClick: (position:Int)-> Unit): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private val list = arrayListOf<News>()
    private var boolean:Boolean = false

    inner class ViewHolder(private var binding: ItemNewsBinding):
        RecyclerView.ViewHolder(binding.root) {

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

    fun replaceItem(news: News,poss: Int) {
//        list.set(poss,news)
        list[poss] = news
        notifyItemChanged(poss)
//        boolean =true
    }


}