package com.example.goodbook.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.goodbook.data.DAO.GoodBookDao
import com.example.goodbook.data.DAO.PostDao

class StarModel(private val goodBookDao: GoodBookDao) :ViewModel() {

    fun totalStart(postID: Int): LiveData<Int> {
        return goodBookDao.getRating(postID).asLiveData()
    }

    fun totalCount(postID: Int): LiveData<Int> {
        return goodBookDao.getRatingCount(postID).asLiveData()
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