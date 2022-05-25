package com.example.goodbook.ui.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.example.goodbook.data.DAO.GoodBookDao
import com.example.goodbook.model.*

class HomeViewModel(private val goodBookDao : GoodBookDao) : ViewModel() {

    val allCategories : LiveData<List<BookCategory>> = goodBookDao.getAllCategories().asLiveData()

    lateinit var user: User

    lateinit var searchedposts: LiveData<List<Post>>

    public fun getAllPostRelatedTo(categoryId: Int): LiveData<List<Post>> {
        return goodBookDao.getAllPostsRelatedToCategoryById(categoryId).asLiveData()
    }

    public fun getAllMostRatePosts(): LiveData<List<Post>> {
        return goodBookDao.getAllMostRatePosts().asLiveData()
    }

    public fun getAllMostRecentlyPosts(): LiveData<List<Post>> {
        return goodBookDao.getAllMostRecentlyPosts().asLiveData()
    }

    public fun getUserById(id: Int): LiveData<User> {
        return goodBookDao.getUser(id).asLiveData()
    }

    public fun getPostsRelatedToSearchedKeyword(keyword: String): LiveData<List<Post>> {
        val formattedKeyword = "%$keyword%"
        return goodBookDao.getPostsRelatedToKeyWord(formattedKeyword).asLiveData()
    }

    public fun get7PostsForCategories(lifecycleOwner: LifecycleOwner): MutableList<CategoryWithPost> {
        var allCategoriesWithPosts: MutableList<CategoryWithPost> = mutableListOf()

        //Lay cac post co nhieu nguoi rate nhat
        allCategoriesWithPosts.add(CategoryWithPost("Được đánh giá nhiều", -1,
            goodBookDao.get7MostRatePosts().asLiveData()))
        Log.d(TAG, "get7PostsForCategories: ${allCategoriesWithPosts.size}")

        //Lay cac post duoc dang gan day nhat
        allCategoriesWithPosts.add(CategoryWithPost("Gần đây", -2,
            goodBookDao.get7MostRecentlyPosts().asLiveData()))

        allCategories.observe(lifecycleOwner) { listOfCategories->
            listOfCategories.forEach {
                Log.d(TAG, "get7PostsForCategories: ${it.type}")
                val title = it.type
                val id = it.id
                val posts = goodBookDao.get7PostsRelateToCategory(it.id).asLiveData()

                allCategoriesWithPosts.add(CategoryWithPost(title, id, posts))
            }
        }
        return allCategoriesWithPosts
    }

//    public fun getRatingOfPost(post_id: Int): LiveData<Double> {
//        return goodBookDao.getPost(post_id).asLiveData()
//    }

    public suspend fun insertUser (user: User) {
        goodBookDao.insert(user)
    }

    public suspend fun insertPost (post: Post) {
        goodBookDao.insert(post)
    }

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