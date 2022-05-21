package com.example.goodbook.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.goodbook.data.DAO.GoodBookDao
import com.example.goodbook.model.*

class HomeViewModel(private val goodBookDao : GoodBookDao) : ViewModel() {

    /**
    val allCategories : LiveData<List<BookCategory>> = goodBookDao.getAllCategories().asLiveData()

    lateinit var user: User

    lateinit var searchedposts: LiveData<List<Post>>

    public fun getPosts(keyword: String) : LiveData<List<Post>> {

        //TODO(Sửa để tìm kiếm sách theo keyword)
        searchedposts = goodBookDao.getAllPosts().asLiveData()
        return searchedposts
    }
    */
}

class HomeViewModelFactory(private val goodBookDao: GoodBookDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(goodBookDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}