package com.example.goodbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "likes", primaryKeys = ["user_id", "comment_id"],
foreignKeys = [
    ForeignKey(entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = Comment::class,
            parentColumns = ["id"],
            childColumns = ["comment_id"],
            onDelete = ForeignKey.CASCADE)
])
data class Like (
    @ColumnInfo(name = "user_id")
    val userId: Int,

    @ColumnInfo(name = "comment_id")
    val commentId: Int,

    val like_or_dis: Int,
    val time: Date,
    val seen: Int
)