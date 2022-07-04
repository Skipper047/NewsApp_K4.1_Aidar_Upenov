package com.example.newsapp_k41_aidar_upenov

import android.app.Application
import androidx.room.Room
import com.example.newsapp_k41_aidar_upenov.room.AppDataBase

class App : Application() {

    companion object{
        lateinit var database: AppDataBase
    }


    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, AppDataBase::class.java, "database").allowMainThreadQueries().build()
    }
}