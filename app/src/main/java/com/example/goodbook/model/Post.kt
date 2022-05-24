package com.example.goodbook.model

import android.graphics.Bitmap
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
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
    val title: String,

    @Nullable
    val img_scr: Bitmap? = null,

    @NonNull
    val book_writer: String,
    val description: String,
    val user: Int,
    val category: Int,
    val time: Date,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

) {
    @Ignore
    var totalStar: Double = 0.0

    fun setStar(star: Double) {
        totalStar = star
    }
}