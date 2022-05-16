package com.example.goodbook.model

data class BookCategory (
    val type: String,
    val books: MutableList<Book>
)