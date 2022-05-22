package com.example.goodbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "comments",
        foreignKeys = [ForeignKey(entity = User::class,
                                parentColumns = ["id"],
                                childColumns = ["user_id"],
                                onDelete = ForeignKey.CASCADE),
                        ForeignKey(entity = Post::class,
                                parentColumns = ["id"],
                                childColumns = ["post_id"],
                                onDelete = ForeignKey.CASCADE)])
data class Comment  (

    @ColumnInfo(name = "user_id")
    val userId: Int,

    @ColumnInfo(name = "post_id")
    val postId: Int,

    val description: String,
    val time: Date,
    val seen: Int,

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)