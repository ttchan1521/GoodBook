package com.example.goodbook.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goodbook.GoodBookApplication
import com.example.goodbook.adpater.MyPostAdapter


import com.example.goodbook.databinding.FragmentMyPostBinding
import com.example.goodbook.ui.AddPostActivity
import com.example.goodbook.ui.viewmodel.PostModel
import com.example.goodbook.ui.viewmodel.PostViewModelFactory
import com.example.goodbook.ui.viewmodel.StarModel
import com.example.goodbook.ui.viewmodel.StarViewModelFactory


class MyPostFragment : Fragment() {
    private var _binding: FragmentMyPostBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private val postModel: PostModel by activityViewModels() {
        PostViewModelFactory(
            (activity?.application as GoodBookApplication).database.postDao()
        )
    }

    private val starModel: StarModel by activityViewModels() {
        StarViewModelFactory(
            (activity?.application as GoodBookApplication).database.goodBookDao()
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMyPostBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.myPostList
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        val adapter = MyPostAdapter(starModel = starModel)
        recyclerView.adapter = adapter

        val userId = activity?.intent?.extras?.getInt("userId")

        postModel.getMyPost(userId!!).observe(this.viewLifecycleOwner) {item ->
            item?.let {
                for (i in it) {
                    i.setStar(getAvg(i.id))
                    Log.d("Trang", i.totalStar.toString())

                }
                adapter.submitList(it)
            }
        }

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(context, AddPostActivity::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun getAvg(postID: Int) :Double {
        var total: Int = 0
        starModel.totalStart(postID).observe(this.viewLifecycleOwner) { item ->
            item?.let {
                total = it
            }
        }
        var count: Int = 1
        starModel.totalCount(postID).observe(this.viewLifecycleOwner) { item ->
            item?.let {
                count = it
            }
        }
        return total.toDouble() / count
    }
}