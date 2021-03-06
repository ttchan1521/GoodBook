package com.example.goodbook.ui

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.example.goodbook.GoodBookApplication
import com.example.goodbook.R
import com.example.goodbook.adpater.HomeBookCategoriesItemAdapter
import com.example.goodbook.databinding.ActivityLoginBinding
import com.example.goodbook.databinding.ActivitySavePostBinding
import com.example.goodbook.model.Post
import com.example.goodbook.ui.viewmodel.*
import kotlin.properties.Delegates

class SavedPostActivity : AppCompatActivity() {
    private val viewModel: SavePostViewModel by viewModels() {
        SavePostViewModelFactory(
            (application as GoodBookApplication).database.
            goodBookDao()
        )
    }

    private val homeViewModel: HomeViewModel by viewModels() {
        HomeViewModelFactory(
            (application as GoodBookApplication).database.
            goodBookDao()
        )
    }
    private lateinit var pageBefore: String
    private var userId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySavePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "Saved Post HELLO")

        val adapter = HomeBookCategoriesItemAdapter(homeViewModel, this) { post: Post ->
            val postDetailIntent = Intent(this, DetailPostActivity::class.java)
            Log.d(TAG, "onCreate: adapter ${post.id}")
            postDetailIntent.putExtra("post", post.id)
            postDetailIntent.putExtra("userId", userId)
            startActivity(postDetailIntent)
        }

        pageBefore = intent?.extras?.getString("oldActivity")!!
        userId = intent?.extras?.getInt("userId")!!

        viewModel.getSavedPostsByUserId(userId).observe(this) {

            val convertedList: List<Post> = it
            adapter.submitList(convertedList)
            Log.d(TAG, "hello4")
        }

        binding.savedPostRecycleView.adapter = adapter
    }

    fun back(view: View) {
        val activityClass = when (pageBefore) {
            //TODO N???u c?? page n??o n???a th?? n??m v?? ????y
            "MainActivity" -> MainActivity::class.java
            else -> MainActivity::class.java
        }

        val backIntent = Intent(this, activityClass)
        backIntent.putExtra("userId", userId)
        viewModel.getUserById(userId!!).observe(this) {
            backIntent.putExtra("userFullName", it.name)
            backIntent.putExtra("userAvt", it.avt)
        }
        startActivity(backIntent)
    }
}