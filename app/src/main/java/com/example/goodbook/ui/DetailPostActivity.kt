package com.example.goodbook.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goodbook.R
import com.example.goodbook.ui.adpater.CmtAdapter
import com.example.goodbook.databinding.ActivityDetailPostBinding
import com.ms.square.android.expandabletextview.ExpandableTextView


class DetailPostActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val expTv = binding.expandTextView.findViewById<View>(R.id.expand_text_view) as ExpandableTextView
        expTv.text = getString(R.string.post_content)

        recyclerView = binding.listCmt
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CmtAdapter()

    }
}