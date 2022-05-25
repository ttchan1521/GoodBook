package com.example.goodbook.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goodbook.GoodBookApplication
import com.example.goodbook.R
import com.example.goodbook.adpater.CmtAdapter
import com.example.goodbook.databinding.ActivityDetailPostBinding
import com.example.goodbook.ui.viewmodel.*
import com.ms.square.android.expandabletextview.ExpandableTextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus


class DetailPostActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    private val postModel: PostModel by viewModels() {
        PostViewModelFactory(
            (application as GoodBookApplication).database.postDao()
        )
    }

    private val starModel: StarModel by viewModels() {
        StarViewModelFactory(
            (application as GoodBookApplication).database.goodBookDao()
        )
    }

    private val cmtModel: CommentModel by viewModels() {
        CommentViewModelFactory(
            (application as GoodBookApplication).database.goodBookDao()
        )
    }
    lateinit var binding: ActivityDetailPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val expTv = binding.expandTextView.findViewById<View>(R.id.expand_text_view) as ExpandableTextView
        expTv.text = getString(R.string.post_content)

        recyclerView = binding.listCmt
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CmtAdapter()
        recyclerView.adapter = adapter

        binding.backBtn.setOnClickListener {
            finish()
        }

        val post_id = intent?.extras?.getInt("post")

        cmtModel.getCmtPost(post_id!!).observe(this, Observer { item ->
            item.let {
                adapter.submitList(it)
            }
        })


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

        val userId = intent?.extras?.getInt("userId")

        starModel.getStarUser(post_id, userId!!)?.observe(this, Observer { item ->
            item.let {
                val star = it - 1
                for (i in 0..star) {
                    (binding.rating.getChildAt(i) as ImageView).setImageResource(R.drawable.ic_baseline_star_24)

                }
            }
        })

        binding.sendCmt.setOnClickListener {
            cmtModel.addCmt(post_id, userId, binding.textMycmt.text.toString())
            binding.textMycmt.text.clear()
        }

        binding.star1.setOnClickListener{
            setRating(0)
        }
        binding.star2.setOnClickListener{
            setRating(1)
        }
        binding.star3.setOnClickListener{
            setRating(2)
        }
        binding.star4.setOnClickListener{
            setRating(3)
        }
        binding.star5.setOnClickListener{
            setRating(4)
        }
        
        binding.markAsSave.setOnClickListener {
            if (userId != null) {
                postModel.savePost(userId, post_id)
            }

        }
    }

    fun setRating(star: Int) {
        val userId = intent?.extras?.getInt("userId")
        val postId = intent?.extras?.getInt("post")
        for (i in 0..star) {
            (binding.rating.getChildAt(i) as ImageView).setImageResource(R.drawable.ic_baseline_star_24)
            starModel.addRating(postId!!, userId!!, star + 1)
        }
    }

}
