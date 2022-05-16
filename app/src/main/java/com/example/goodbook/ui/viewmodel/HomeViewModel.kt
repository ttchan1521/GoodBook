package com.example.goodbook.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.goodbook.model.Book
import com.example.goodbook.model.BookCategory
import androidx.lifecycle.*

class HomeViewModel: ViewModel() {

    val book1 = Book(0, "Harry Potter va hon da phu thuy", "hp_hondaphuthuy", 4.5,"J.K.Rowling", "avt1")
    val book2 = Book(1, "Harry Potter va hon da phu thuy", "hp_hondaphuthuy", 4.5,"J.K.Rowling", "avt1")
    val book3 = Book(0, "Harry Potter va hon da phu thuy", "hp_hondaphuthuy", 4.5,"J.K.Rowling", "avt1")
    val book4 = Book(1, "Harry Potter va hon da phu thuy", "hp_hondaphuthuy", 4.5,"J.K.Rowling", "avt1")
    val bCategoriy1 = BookCategory("Gần đây", listOf(book1, book2, book3, book4) as MutableList<Book>)
    val bCategoriy2 = BookCategory("Đánh giá cao", listOf(book1, book2, book3, book4) as MutableList<Book>)

    val allCategories: List<BookCategory> = listOf(bCategoriy1, bCategoriy2)

}

class HomeViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}