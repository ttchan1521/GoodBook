package com.example.goodbook.adpater

import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.goodbook.GoodBookApplication
import com.example.goodbook.R
import com.example.goodbook.databinding.MyposstBookItemBinding
import com.example.goodbook.model.Post
import com.example.goodbook.ui.AddPostActivity
import com.example.goodbook.ui.DetailPostActivity
import com.example.goodbook.ui.viewmodel.PostModel
import com.example.goodbook.ui.viewmodel.PostViewModelFactory
import com.example.goodbook.ui.viewmodel.StarModel

class MyPostAdapter(val starModel: StarModel): ListAdapter<Post, MyPostAdapter.MyPostHolder>(DiffCallback) {


    class MyPostHolder(private var binding: MyposstBookItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.bookTitle.text = post.title
            binding.bookWriter.text = post.book_writer
            binding.imageBook.setImageBitmap(post.img_scr)
            binding.starCount.text = post.totalStar.toString()


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostHolder {
        var adapterLayout = MyposstBookItemBinding.inflate(
            LayoutInflater.from(parent.context)
        )


        return MyPostHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MyPostHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailPostActivity::class.java)
            intent.putExtra("post",current.id)

            context.startActivity(intent)
        }
        holder.bind(current)
    }


    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    fun passData(context: Context, current: Post) {

    }

}
