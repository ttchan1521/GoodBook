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
import com.example.goodbook.ui.viewmodel.CategoryModel
import com.example.goodbook.ui.viewmodel.CategoryViewModelFactory
import com.example.goodbook.ui.viewmodel.SavePostViewModel
import com.example.goodbook.ui.viewmodel.SavePostViewModelFactory
import kotlin.properties.Delegates

class SavedPostActivity : AppCompatActivity() {
    private val viewModel: SavePostViewModel by viewModels() {
        SavePostViewModelFactory(
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

        Log.d(TAG, "hello")

        val adapter = HomeBookCategoriesItemAdapter(viewModel, this) { post: Post ->
            val postDetailIntent = Intent(this, DetailPostActivity::class.java)
            postDetailIntent.putExtra("post", post.id)
            postDetailIntent.putExtra("userId", userId)
            startActivity(postDetailIntent)
        }

        pageBefore = this.intent?.extras?.getString("oldActivity")!!
        userId = this.intent?.extras?.getInt("userId")!!

        if (userId!! != null) {
            viewModel.getSavedPostsByUserId(userId!!).observe(this) {
                adapter.submitList(it)
            }
        }

        binding.savedPostRecycleView.adapter = adapter
    }

    fun back(view: View) {
        val activityClass = when (pageBefore) {
            //TODO Nếu có page nào nữa thì ném vô đây
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