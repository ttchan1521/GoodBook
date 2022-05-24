package com.example.goodbook.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goodbook.GoodBookApplication
import com.example.goodbook.R
import com.example.goodbook.adpater.CmtAdapter
import com.example.goodbook.databinding.ActivityDetailPostBinding
import com.example.goodbook.ui.viewmodel.PostModel
import com.example.goodbook.ui.viewmodel.PostViewModelFactory
import com.ms.square.android.expandabletextview.ExpandableTextView


class DetailPostActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    private val postModel: PostModel by viewModels() {
        PostViewModelFactory(
            (application as GoodBookApplication).database.postDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val expTv = binding.expandTextView.findViewById<View>(R.id.expand_text_view) as ExpandableTextView
        expTv.text = getString(R.string.post_content)

        recyclerView = binding.listCmt
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CmtAdapter()

        binding.backBtn.setOnClickListener {
            finish()
        }

        val post_id = intent?.extras?.getInt("post")

        postModel.getDetailPost(post_id!!).observe(this, Observer { item ->
            item.let {
                binding.title.text = it.title
                binding.author.text = it.book_writer
                binding.avatar.setImageBitmap(it.avt)
                binding.bookImg.setImageBitmap(it.img_scr)
                binding.starCount.text = it.star.toString()
                expTv.text = it.description
            }
        })
    }
}