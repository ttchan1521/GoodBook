package com.example.goodbook.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class BookCategory (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @NonNull
    val type: String,
)

