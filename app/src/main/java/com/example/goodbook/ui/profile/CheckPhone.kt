package com.example.goodbook.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.goodbook.R

class CheckPhone : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_phone)

        supportActionBar!!.title = "Điện thoại"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setBackgroundDrawable(resources.getDrawable(R.drawable.shape2))
    }
}