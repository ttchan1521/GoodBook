package com.example.goodbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications")
data class Notification(

    @PrimaryKey @ColumnInfo(name = "id")
    val  notificationID: Int,

    @ColumnInfo(name = "send_uer_id")
    val sendUserId: Int,

    @ColumnInfo(name = "post_id")
    val postId: Int,

    @ColumnInfo(name = "notiftype_id")
    val notificationTypeId : Int,

    val seen: Boolean
    )