package com.example.newsapp_k41_aidar_upenov.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp_k41_aidar_upenov.models.News

@Database(entities = [News::class], version = 1)
abstract class AppDataBase :RoomDatabase(){
    abstract fun newsDao(): NewsDao

}