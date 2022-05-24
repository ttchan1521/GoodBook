package com.example.goodbook.adpater

import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.goodbook.GoodBookApplication
import com.example.goodbook.R
import com.example.goodbook.databinding.MypostBookItemBinding
import com.example.goodbook.model.Post
import com.example.goodbook.ui.AddPostActivity
import com.example.goodbook.ui.DetailPostActivity
import com.example.goodbook.ui.viewmodel.PostModel
import com.example.goodbook.ui.viewmodel.PostViewModelFactory
import com.example.goodbook.ui.viewmodel.StarModel
import kotlinx.android.synthetic.main.mypost_book_item.view.*

class MyPostAdapter(val starModel: StarModel): ListAdapter<Post, MyPostAdapter.MyPostHolder>(DiffCallback) {


    class MyPostHolder(private var binding: MypostBookItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val star1: ImageView = itemView.star1
        private val star2: ImageView = itemView.star2
        private val star3: ImageView = itemView.star3
        private val star4: ImageView = itemView.star4
        private val star5: ImageView = itemView.star5

        fun bind(post: Post) {
            binding.bookTitle.text = post.title
            binding.bookWriter.text = post.book_writer
            binding.imageBook.setImageBitmap(post.img_scr)
            binding.starCount.text = post.totalStar.toString()

            when (post.totalStar) {
                1.0 -> {
                    star1.setImageResource(R.drawable.ic_baseline_star_24)
                }
                1.5 -> {
                    star1.setImageResource(R.drawable.ic_baseline_star_24)
                    star2.setImageResource(R.drawable.ic_baseline_star_half_24)
                }
                2.0 -> {
                    star1.setImageResource(R.drawable.ic_baseline_star_24)
                    star2.setImageResource(R.drawable.ic_baseline_star_24)
                }
                2.5 -> {
                    star1.setImageResource(R.drawable.ic_baseline_star_24)
                    star2.setImageResource(R.drawable.ic_baseline_star_24)
                    star3.setImageResource(R.drawable.ic_baseline_star_half_24)
                }
                3.0 -> {
                    star1.setImageResource(R.drawable.ic_baseline_star_24)
                    star2.setImageResource(R.drawable.ic_baseline_star_24)
                    star3.setImageResource(R.drawable.ic_baseline_star_24)
                }
                3.5 -> {
                    star1.setImageResource(R.drawable.ic_baseline_star_24)
                    star2.setImageResource(R.drawable.ic_baseline_star_24)
                    star3.setImageResource(R.drawable.ic_baseline_star_24)
                    star4.setImageResource(R.drawable.ic_baseline_star_half_24)
                }
                4.0 -> {
                    star1.setImageResource(R.drawable.ic_baseline_star_24)
                    star2.setImageResource(R.drawable.ic_baseline_star_24)
                    star3.setImageResource(R.drawable.ic_baseline_star_24)
                    star4.setImageResource(R.drawable.ic_baseline_star_24)
                }
                4.5 -> {
                    star1.setImageResource(R.drawable.ic_baseline_star_24)
                    star2.setImageResource(R.drawable.ic_baseline_star_24)
                    star3.setImageResource(R.drawable.ic_baseline_star_24)
                    star4.setImageResource(R.drawable.ic_baseline_star_24)
                    star5.setImageResource(R.drawable.ic_baseline_star_half_24)
                }
                5.0 -> {
                    star1.setImageResource(R.drawable.ic_baseline_star_24)
                    star2.setImageResource(R.drawable.ic_baseline_star_24)
                    star3.setImageResource(R.drawable.ic_baseline_star_24)
                    star4.setImageResource(R.drawable.ic_baseline_star_24)
                    star5.setImageResource(R.drawable.ic_baseline_star_24)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostHolder {
        var adapterLayout = MypostBookItemBinding.inflate(
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
