package com.example.goodbook.ui.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.*
import com.example.goodbook.R
import com.example.goodbook.data.DAO.PostDao
import com.example.goodbook.model.Post
import com.example.goodbook.model.PostDetail
import com.example.goodbook.model.SavedBook
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class PostModel(private val postDao: PostDao) : ViewModel(){

    val allPost: LiveData<List<Post>> = postDao.getAllPosts().asLiveData()

    fun getMyPost(userId: Int): LiveData<List<Post>> {
        return postDao.getMyPost(userId).asLiveData()
    }

    fun addNewPost(title: String, img: Bitmap?, author: String, description: String,
                    user: Int, category: Int) {
        val post = Post(title, img, author, description, user, category, Date())
        insertPost(post)
    }

    private fun insertPost(post: Post) {
        viewModelScope.launch {
            postDao.insert(post)
        }
    }

    fun getDetailPost(postId: Int): LiveData<PostDetail> {
        return postDao.getDetailPost(postId).asLiveData()
    }

    fun savePost(user_id: Int, post_id: Int) {
        val saveInstance = SavedBook(user_id, post_id)
        viewModelScope.launch(Dispatchers.IO) {
            postDao.insert(saveInstance)
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