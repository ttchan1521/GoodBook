package com.example.goodbook.adpater

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.goodbook.R
import com.example.goodbook.ui.AddPostActivity
import com.example.goodbook.ui.DetailPostActivity

class MyPostAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val LAYOUT_ONE = 0;
    private val LAYOUT_TWO = 1;

    class MyPostHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }

    class AddPostHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_item, parent, false)

        if (viewType == 1) {
            adapterLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.add_post_item, parent, false)

            return AddPostHolder(adapterLayout)
        }
        return MyPostHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            var intent = Intent(context, DetailPostActivity::class.java)
            if (position == 5) {
                intent = Intent(context, AddPostActivity::class.java)
            }

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 5) {
            return LAYOUT_TWO
        }
        return LAYOUT_ONE
    }

}