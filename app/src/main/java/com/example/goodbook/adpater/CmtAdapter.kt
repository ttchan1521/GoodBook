package com.example.goodbook.adpater

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.goodbook.R
import com.example.goodbook.databinding.CmtItemBinding
import com.example.goodbook.model.Comment
import com.example.goodbook.model.DetailComment
import com.example.goodbook.model.Post
import com.example.goodbook.ui.DetailPostActivity

class CmtAdapter: ListAdapter<DetailComment, CmtAdapter.CmtHolder>(DiffCallback) {

    class CmtHolder(private var binding: CmtItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cmt: DetailComment) {
            binding.username.text = cmt.name
            binding.cmtContent.text = cmt.description
            binding.avt.setImageBitmap(cmt.avt)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CmtHolder {
        val adapterLayout = CmtItemBinding.inflate(
            LayoutInflater.from(parent.context)
        )
        return CmtHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: CmtHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)


    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<DetailComment>() {
            override fun areItemsTheSame(oldItem: DetailComment, newItem: DetailComment): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: DetailComment, newItem: DetailComment): Boolean {
                return oldItem.id== newItem.id
            }
        }
    }
}