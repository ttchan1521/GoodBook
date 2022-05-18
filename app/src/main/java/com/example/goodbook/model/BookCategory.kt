package com.example.goodbook.model

data class BookCategory (
    val type: CategoryType,
    val title: String,
    val books: MutableList<Book>
)

public enum class CategoryType {
    RECENTLY, MOST_READ
}
