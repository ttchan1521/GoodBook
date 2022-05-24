package com.example.goodbook.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.goodbook.databinding.BookItemBinding
import com.example.goodbook.model.Post
import com.example.goodbook.ui.viewmodel.HomeViewModel

class HomeBookCategoriesItemAdapter (
    private val viewModel: ViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val clickListener: (Post) -> Unit
) : ListAdapter<Post, HomeBookCategoriesItemAdapter.PostViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PostViewHolder(
            BookItemBinding.inflate(layoutInflater, parent, false),
            lifecycleOwner,
            viewModel
        )
    }

    class PostViewHolder(
        private var binding: BookItemBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val viewModel: ViewModel
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(post : Post) {

            binding.post = post

            (viewModel as HomeViewModel).getRatingOfPost(post.id).observe(lifecycleOwner) {
//                val rating_star = it
//
//                for (i in 0..rating_star) {
//                    (binding.starFrameLayout.getChildAt(i) as ImageView).setImageResource(R.drawable.ic_baseline_star_24)
//                }
//
//                for (i in rating_star..5) {
//                    (binding.starFrameLayout.getChildAt(i) as ImageView).setImageResource(R.drawable.ic_baseline_star_border_24)
//                }
            }

            (viewModel as HomeViewModel).getUserById(post.user).observe(lifecycleOwner) {
                binding.userWritten = it
            }

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