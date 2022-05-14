package com.example.goodbook.adpater

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.goodbook.R

class CmtAdapter: RecyclerView.Adapter<CmtAdapter.CmtHolder>() {

    val data : List<String> = listOf("Chan", "Vũ Thị Tâm", "Quỳnh Tạ", "Tuấn bùi Duy", "hồng lươn")

    class CmtHolder(private val view : View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.username)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CmtHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.cmt_item, parent, false)
        Log.d("Trang", "jhjh")

        return CmtHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: CmtHolder, position: Int) {
        val item = data[position]
        holder.name.text = item

    }

    override fun getItemCount(): Int {
        return data.size
    }
}