package com.example.goodbook.ui.viewmodel

import androidx.lifecycle.*
import com.example.goodbook.data.DAO.GoodBookDao
import com.example.goodbook.model.Post
import com.example.goodbook.model.User

class SavePostViewModel (private val goodBookDao: GoodBookDao) : ViewModel() {

    fun getUserById(id: Int): LiveData<User> {
        return goodBookDao.getUser(id).asLiveData()
    }

    fun getSavedPostsByUserId(id: Int) : LiveData<List<Post>> {
        return goodBookDao.getSavedPostsByUserId(id).asLiveData()
    }


}

class SavePostViewModelFactory(private val goodBookDao: GoodBookDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavePostViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SavePostViewModel(goodBookDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}