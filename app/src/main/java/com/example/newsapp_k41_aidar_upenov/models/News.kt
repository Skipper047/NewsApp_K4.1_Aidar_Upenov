package com.example.newsapp_k41_aidar_upenov.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity
data class News(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var createdAt: Long
): Serializable
