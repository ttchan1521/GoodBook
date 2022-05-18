package com.example.goodbook.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goodbook.R
import com.example.goodbook.adpater.MyPostAdapter
import com.example.goodbook.adpater.NotificationAdapter


import com.example.goodbook.data.DataNotification
import com.example.goodbook.databinding.FragmentMyPostBinding
import com.example.goodbook.databinding.FragmentNotificationBinding


class MyPostFragment : Fragment() {
    private var _binding: FragmentMyPostBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

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
        recyclerView.adapter = MyPostAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}