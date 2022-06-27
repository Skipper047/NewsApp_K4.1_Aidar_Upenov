package com.example.newsapp_k41_aidar_upenov.models

import java.io.Serializable

data class News(
    var title: String,
    var createdAt: Long
): Serializable
