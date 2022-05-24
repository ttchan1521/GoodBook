package com.example.goodbook.ui.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.example.goodbook.data.DAO.GoodBookDao
import com.example.goodbook.data.GoodBookDatabase
import com.example.goodbook.model.User

class LoginViewModel(private val goodBookDao: GoodBookDao) : ViewModel() {

    suspend fun getUserByMailOrPhone(mail_phone: String): LiveData<User> {
//        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "getUserByMailOrPhone: xyz")
            val user = goodBookDao.getUser(mail_phone).asLiveData()
            Log.d(TAG, "getUserByMailOrPhone: " + user.value?.name)
            return user
//        }
    }

    suspend fun checkLoginUser(mail_phone: String, pass: String): Boolean {
        var is_available = false
        Log.d(TAG, "checkUser: $mail_phone, $pass")
        val user = goodBookDao.getUser(mail_phone, pass).asLiveData()

        if (user.value?.userId != null) {
            Log.d(TAG, "checkUser: ok")
            is_available = true
        }

        return is_available
    }
}

class LoginViewModelFactory(private val goodBookDao: GoodBookDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(goodBookDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}