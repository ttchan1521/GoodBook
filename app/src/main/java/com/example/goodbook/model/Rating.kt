package com.example.goodbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "ratings", primaryKeys = ["user_id", "post_id"],
        foreignKeys = [ForeignKey(entity = User::class,
                                    parentColumns = ["id"],
                                    childColumns = ["user_id"],
                                    onDelete = ForeignKey.CASCADE),
                        ForeignKey(entity = Post::class,
                                    parentColumns = ["id"],
                                    childColumns = ["post_id"],
                                    onDelete = ForeignKey.CASCADE)])
data class Rating (
    @ColumnInfo(name = "user_id")
    val userId: Int,

    @ColumnInfo(name = "post_id")
    val postId: Int,

    val star_quantity: Int,
    val time: Date,
    val seen: Int
)