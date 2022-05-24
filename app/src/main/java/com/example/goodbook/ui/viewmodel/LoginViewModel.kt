package com.example.goodbook.ui.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.example.goodbook.data.DAO.GoodBookDao
import com.example.goodbook.data.GoodBookDatabase
import com.example.goodbook.model.BookCategory
import com.example.goodbook.model.User

class LoginViewModel(private val goodBookDao: GoodBookDao, private val lifecycleOwner: LifecycleOwner) : ViewModel() {

    val allUsers : LiveData<List<User>> = goodBookDao.getAllUsers().asLiveData()

    suspend fun getUserByMailOrPhone(mail_phone: String, pass: String): LiveData<User> {
            val user = goodBookDao.getUser(mail_phone, pass).asLiveData()
            return user
    }

}

class LoginViewModelFactory(private val goodBookDao: GoodBookDao, private val lifecycleOwner: LifecycleOwner) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(goodBookDao, lifecycleOwner) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}