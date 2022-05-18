package com.example.goodbook.ui.profile

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.goodbook.R

class PhoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone)

        supportActionBar!!.title = "Điện thoại"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setBackgroundDrawable(resources.getDrawable(R.drawable.shape2))

        val button: Button = findViewById(R.id.send_otp_btn)
        button.setOnClickListener {
            val intent = Intent(this, CheckPhone::class.java)
            startActivity(intent)
        }
    }
}