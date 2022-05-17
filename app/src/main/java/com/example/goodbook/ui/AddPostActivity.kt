package com.example.goodbook.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.goodbook.R
import com.example.goodbook.databinding.ActivityAddPostBinding

class AddPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}