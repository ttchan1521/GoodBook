package com.example.goodbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey @ColumnInfo(name = "id")
    val userId: Int,

    val password: String,
    val name: String,

    @ColumnInfo(name = "phone")
    val phoneNumber: String,
    val email: String,
    val avt: String
)