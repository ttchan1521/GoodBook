package com.example.goodbook.adpater

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.goodbook.databinding.HomeBookCategoriesItemBinding
import com.example.goodbook.model.BookCategory
import com.example.goodbook.model.Post

class HomeListAdapter (
    private val clickListener: (type: String) -> Unit
) : ListAdapter<BookCategory, HomeListAdapter.CategoryViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CategoryViewHolder(
            HomeBookCategoriesItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    class CategoryViewHolder(
        private var binding: HomeBookCategoriesItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(category: BookCategory, clickListener: () -> Unit) {
            binding.category = category

            binding.bookCategoryRecycleview.adapter = HomeBookCategoriesItemAdapter { post: Post ->
                //TODO()
            }
            //(binding.bookCategoryRecycleview.adapter as HomeBookCategoriesItemAdapter).submitList(category.books)

            binding.moreText.setOnClickListener {
                clickListener()
            }

            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)

        //TODO(fix type of category)
        //holder.bind(category) { clickListener(category.type) }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<BookCategory>() {
        override fun areItemsTheSame(oldItem: BookCategory, newItem: BookCategory): Boolean {
            return oldItem.type == newItem.type
        }

        override fun areContentsTheSame(oldItem: BookCategory, newItem: BookCategory): Boolean {
            return oldItem == newItem
        }

    }

}