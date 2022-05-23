package com.example.goodbook.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class BookCategory (
    @NonNull
    val type: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

