package com.example.goodbook.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class Post (
    @PrimaryKey
    val id: Int,
    val title: String,
    val img_scr: String,
    val rate: Double,
    val book_writer: String,
    val review_writer_img: String
)