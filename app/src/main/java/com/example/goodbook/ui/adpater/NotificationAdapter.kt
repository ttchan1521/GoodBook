package com.example.goodbook.ui.adpater

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.goodbook.R
import com.example.goodbook.model.Notification
import com.example.goodbook.ui.DetailPostActivity

class NotificationAdapter(private val context: Context, private val dataset: List<Notification>)
    : RecyclerView.Adapter<NotificationAdapter.NotificationHolder>() {

        class NotificationHolder(val view: View) : RecyclerView.ViewHolder(view) {
            val layout = view.findViewById<LinearLayout>(R.id.noti_holder)
            val avatar = view.findViewById<ImageView>(R.id.noti_avatar)
            val textView = view.findViewById<TextView>(R.id.noti_content)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.notification_list, parent, false)

        return NotificationHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: NotificationHolder, position: Int) {
        val item = dataset[position]
        if (position %2 ==0) {
            holder.layout.setBackgroundColor(context.resources.getColor(R.color.noti_seen))
        }

        holder.avatar.setImageResource(R.drawable.noti_avatar)
        holder.textView.text = HtmlCompat.fromHtml(context.resources.getString(item.stringResourceID), HtmlCompat.FROM_HTML_MODE_LEGACY)

        holder.layout.setOnClickListener {
            val context = holder.view.context
            val intent = Intent(context, DetailPostActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size


    }
}