package com.example.goodbook.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "posts",
    foreignKeys = arrayOf(ForeignKey(entity = User::class,
                    parentColumns = arrayOf("id"),
                    childColumns = arrayOf("user"),
                    onDelete = ForeignKey.CASCADE),
                    ForeignKey(entity = BookCategory::class,
                    parentColumns = arrayOf("id"),
                    childColumns = arrayOf("category"),
                    onDelete = ForeignKey.CASCADE))
)
data class Post (
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @NonNull
    val title: String,
    val img_scr: String,

    val book_writer: String,
    val description: String,
    val user: Int,
    val category: Int,
    val time: Date
)