package com.example.goodbook.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.goodbook.R

class ResetPassword1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password1)

        supportActionBar!!.title = "Đặt lại mật khẩu"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setBackgroundDrawable(resources.getDrawable(R.drawable.shape2))
    }
}