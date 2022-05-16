package com.example.goodbook.model

data class Book (
    val id: Long = 0,
    val title: String,
    val img_scr: String,
    val rate: Double,
    val book_writer: String,
    val review_writer_img: String
)