package com.example.goodbook.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.goodbook.model.Book
import com.example.goodbook.model.BookCategory
import androidx.lifecycle.*
import com.example.goodbook.model.CategoryType
import com.example.goodbook.model.User
import com.example.goodbook.ui.fragment.PageType

class HomeViewModel: ViewModel() {

    val user = User("Nguyễn Văn A", 1736675422, "jagsf@gmail.com", "avt2")

    lateinit var searchedbooks: List<Book>

    val book1 = Book(0, "Harry Potter va hon da phu thuy", "hp_hondaphuthuy", 4.5,"J.K.Rowling", "avt1")
    val book2 = Book(1, "Harry Potter va hon da phu thuy", "hp_hondaphuthuy", 4.5,"J.K.Rowling", "avt1")
    val book3 = Book(0, "Harry Potter va hon da phu thuy", "hp_hondaphuthuy", 4.5,"J.K.Rowling", "avt1")
    val book4 = Book(1, "Harry Potter va hon da phu thuy", "hp_hondaphuthuy", 4.5,"J.K.Rowling", "avt1")
    val bCategoriy1 = BookCategory(CategoryType.RECENTLY,"Gần đây", listOf(book1, book2, book3, book4) as MutableList<Book>)
    val bCategoriy2 = BookCategory(CategoryType.MOST_READ,"Được yêu thích", listOf(book1, book2, book3, book4) as MutableList<Book>)

    val allCategories: List<BookCategory> = listOf(bCategoriy1, bCategoriy2)

    fun getBooks (keyword: String): List<Book> {
        return listOf<Book>(book1, book3, book2, book4)
    }
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