package com.example.goodbook.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @NonNull
    val password: String,
    @NonNull
    val name: String,

    @ColumnInfo(name = "phone")
    val phoneNumber: String,
    val email: String,
    val avt: String,
    @PrimaryKey(autoGenerate = true)
    @NonNull @ColumnInfo(name = "id")
    var userId: Int = 0
)