package com.example.goodbook.ui.viewmodel

import androidx.lifecycle.*
import com.example.goodbook.data.DAO.GoodBookDao
import com.example.goodbook.data.DAO.PostDao
import com.example.goodbook.model.Rating
import kotlinx.coroutines.launch
import java.util.*

class StarModel(private val goodBookDao: GoodBookDao) :ViewModel() {

    fun totalStart(postID: Int): LiveData<Int> {
        return goodBookDao.getRating(postID).asLiveData()
    }

    fun totalCount(postID: Int): LiveData<Int> {
        return goodBookDao.getRatingCount(postID).asLiveData()
    }

    fun addRating(postID: Int, userId: Int, star: Int) {
        val rate = Rating(userId, postID, star, Date(), 0)
        insertRating(rate)
    }

    fun insertRating(rate: Rating) {
        viewModelScope.launch {
            goodBookDao.insert(rate)
        }
    }

    fun getStarUser(postID: Int, userId: Int): LiveData<Int>? {
        return goodBookDao.getRatingUser(postID, userId).asLiveData()
    }
}

class StarViewModelFactory(private val goodBookDao: GoodBookDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StarModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StarModel(goodBookDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}