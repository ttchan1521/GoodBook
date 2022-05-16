package com.example.goodbook.ui.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.goodbook.databinding.BookItemBinding
import com.example.goodbook.model.Book

class HomeBookCategoriesItemAdapter (
    private val clickListener: (Book) -> Unit
) : ListAdapter<Book, HomeBookCategoriesItemAdapter.BookViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BookViewHolder(
            BookItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    class BookViewHolder(
        private var binding: BookItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(book : Book) {
            binding.book = book
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)

        holder.itemView.setOnClickListener{
            clickListener(book)
        }

        holder.bind(book)
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }

    }
}