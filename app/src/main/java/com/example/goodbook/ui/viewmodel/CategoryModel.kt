package com.example.goodbook.ui.viewmodel

import androidx.lifecycle.*
import com.example.goodbook.data.DAO.GoodBookDao
import com.example.goodbook.data.DAO.PostDao
import com.example.goodbook.data.DataGenerator
import com.example.goodbook.model.BookCategory
import kotlinx.coroutines.launch

class CategoryModel(private val goodBookDao: GoodBookDao) : ViewModel() {

    val allCategories: LiveData<List<BookCategory>> = goodBookDao.getAllCategories().asLiveData()
}

class CategoryViewModelFactory(private val goodBookDao: GoodBookDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CategoryModel(goodBookDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}