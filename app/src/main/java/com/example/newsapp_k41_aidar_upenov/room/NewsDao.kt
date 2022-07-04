package com.example.newsapp_k41_aidar_upenov.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.newsapp_k41_aidar_upenov.models.News

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAll(): List<News>

    @Insert
    fun insert(news:News)

    @Delete
    fun delete(news: News)
}