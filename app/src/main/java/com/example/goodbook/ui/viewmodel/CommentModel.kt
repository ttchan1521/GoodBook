package com.example.goodbook.ui.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.*
import com.example.goodbook.data.DAO.GoodBookDao
import com.example.goodbook.model.Comment
import com.example.goodbook.model.DetailComment
import kotlinx.coroutines.launch
import java.util.*

class CommentModel(private val goodBookDao: GoodBookDao): ViewModel() {

    fun getCmtPost(postId: Int): LiveData<List<DetailComment>> {
        return goodBookDao.getCommentPost(postId).asLiveData()
    }

    fun addCmt(postId: Int, userId: Int, content: String) {
        val cmt = Comment(userId, postId, content, Date(), 0)
        insertCmt(cmt)
    }

    private fun insertCmt(cmt: Comment) {
        viewModelScope.launch {
            goodBookDao.insert(cmt)
        }
    }

}

class CommentViewModelFactory(private val goodBookDao: GoodBookDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CommentModel(goodBookDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}