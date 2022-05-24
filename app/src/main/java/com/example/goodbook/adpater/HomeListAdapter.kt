package com.example.goodbook.adpater

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.goodbook.databinding.HomeBookCategoriesItemBinding
import com.example.goodbook.model.CategoryWithPost
import com.example.goodbook.model.Post
import com.example.goodbook.ui.DetailPostActivity

class HomeListAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: ViewModel,
    private val activity: Activity,
    private val clickListener: (typeId: Int, title: String ) -> Unit
) : ListAdapter<CategoryWithPost, HomeListAdapter.CategoryViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CategoryViewHolder(
            HomeBookCategoriesItemBinding.inflate(layoutInflater, parent, false),
            viewModel
        )
    }

    class CategoryViewHolder(
        private var binding: HomeBookCategoriesItemBinding,
        private val viewModel: ViewModel
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(category: CategoryWithPost, clickListener: () -> Unit, lifecycleOwner: LifecycleOwner, activity: Activity) {
            binding.category = category

            binding.moreText.visibility = VISIBLE

            binding.bookCategoryRecycleview.adapter = HomeBookCategoriesItemAdapter(viewModel, lifecycleOwner) { post: Post ->
                Log.d(ContentValues.TAG, "Detail Post View through Home Page")
                val postDetailIntent = Intent(activity, DetailPostActivity::class.java)
                postDetailIntent.putExtra("post", post.id)
                val userId = activity?.intent?.extras?.getInt("userId")
                postDetailIntent.putExtra("userId", userId)
                activity.startActivity(postDetailIntent)
            }

            //Tải list các post lên recycleview
            category.posts.observe(lifecycleOwner) {
                (binding.bookCategoryRecycleview.adapter as HomeBookCategoriesItemAdapter)
                    .submitList(it)
            }

            binding.moreText.setOnClickListener {
                clickListener()
            }

            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)

        category.posts.observe(lifecycleOwner) {
            if (it.size > 0) {
                holder.bind(category, { clickListener(category.id, category.title) }, lifecycleOwner, activity)
            }
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<CategoryWithPost>() {
        override fun areItemsTheSame(oldItem: CategoryWithPost, newItem: CategoryWithPost): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: CategoryWithPost, newItem: CategoryWithPost): Boolean {
            return oldItem == newItem
        }

    }

}