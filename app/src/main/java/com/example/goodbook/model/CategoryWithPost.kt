package com.example.goodbook.model

import androidx.lifecycle.LiveData

data class CategoryWithPost (
    val title: String,
    val id: Int,
    val posts: LiveData<List<Post>>
)