package com.example.goodbook.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class BookCategory (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val type: String,
    val title: String
)

public enum class CategoryType {
    RECENTLY, MOST_READ
}
