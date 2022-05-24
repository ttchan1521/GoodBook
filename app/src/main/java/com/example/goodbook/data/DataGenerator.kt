package com.example.goodbook.data

import com.example.goodbook.model.BookCategory
import com.example.goodbook.model.User
import java.util.*

class DataGenerator {

    companion object {
        fun getUsers(): List<User>{
            return listOf(
                User("123456", "Noman", "03398729729", "trang1@gmail.com"),
                User("123456", "Aayan", "03198729729", "trang2@gmail.com"),
                User("123456", "Tariqul", "03390729729", "trang3@gmail.com")
            )
        }

        fun getCategory(): List<BookCategory> {
            return listOf(
                BookCategory("Chính trị - Pháp Luật"),
                BookCategory("Khoa học công nghệ - kinh tế"),
                BookCategory("Văn học - Nghệ thuật"),
                BookCategory("Văn hóa Xã hội - Lịch sử"),
                BookCategory("Giáo trình"),
                BookCategory("Tâm lý - Tôn giáo"),
                BookCategory("Sách thiếu nhi")
            )
        }
    }
}