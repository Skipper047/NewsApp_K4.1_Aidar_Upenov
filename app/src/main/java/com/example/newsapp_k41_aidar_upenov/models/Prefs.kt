package com.example.newsapp_k41_aidar_upenov.models

import android.content.Context
import android.widget.EditText

class Prefs (context: Context){

    private val preferences = context.getSharedPreferences("settings",Context.MODE_PRIVATE)

    fun saveState(){
        preferences.edit().putBoolean("isShown",true).apply()
    }

    fun isShown(): Boolean {
        return preferences.getBoolean("isShown",false)
    }

    fun  saveText(text: String){
        preferences.edit().putString("haveText", text).apply()
    }

    fun setText(): String{
        return preferences.getString("haveText", "sssssssss").toString()
    }

    fun saveEdit(){
        preferences.edit().putBoolean("saveText",true).apply()
    }

    fun setEdit(): Boolean{
        return preferences.getBoolean("saveText",false)
    }

}