package com.example.goodbook.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.goodbook.databinding.BookItemBinding
import com.example.goodbook.model.Post

class HomeBookCategoriesItemAdapter (
    private val clickListener: (Post) -> Unit
) : ListAdapter<Post, HomeBookCategoriesItemAdapter.PostViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PostViewHolder(
            BookItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    class PostViewHolder(
        private var binding: BookItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(post : Post) {
            //TODO(Thay Book = Post)
            //binding.book = book
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)

        holder.itemView.setOnClickListener{
            clickListener(post)
        }

        holder.bind(post)
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }

    }
}