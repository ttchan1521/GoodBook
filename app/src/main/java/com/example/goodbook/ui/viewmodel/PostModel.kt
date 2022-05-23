package com.example.goodbook.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.goodbook.data.DAO.PostDao
import com.example.goodbook.model.Post
import kotlinx.coroutines.launch
import java.util.*

class PostModel(private val postDao: PostDao) : ViewModel(){

    fun addNewPost(title: String, img: String, author: String, description: String,
                    user: Int, category: Int) {
        val post = Post(title, img, author, description, user, category, Date())
        insertPost(post)
    }

    private fun insertPost(post: Post) {
        viewModelScope.launch {
            postDao.insert(post)
        }
    }
}

class PostViewModelFactory(private val postDao: PostDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PostModel(postDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}