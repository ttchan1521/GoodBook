package com.example.goodbook.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.goodbook.R

class SavePostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_post)

        supportActionBar!!.title = "Đã lưu"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setBackgroundDrawable(resources.getDrawable(R.drawable.shape2))
    }
}