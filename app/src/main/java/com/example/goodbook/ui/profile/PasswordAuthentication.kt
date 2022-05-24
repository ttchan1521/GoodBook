package com.example.goodbook.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.goodbook.R

class PasswordAuthentication : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_authentication)

        supportActionBar!!.title = "Xác thực mật khẩu"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setBackgroundDrawable(resources.getDrawable(R.drawable.shape2))

        val resetPassBtn: Button = findViewById(R.id.next_to_change_password_btn)
        val forgetPass: TextView = findViewById(R.id.forget_pass)
        val oldPassword: EditText = findViewById(R.id.old_password)
        val password = getIntent().getStringExtra("password")
        resetPassBtn.setOnClickListener {
            if (!password.toString().equals(oldPassword.text.toString())) {
                oldPassword.setError("Mật khẩu không chính xác")
            } else {
                val intent = Intent(this, ResetPassword2Activity::class.java)
                startActivity(intent)
            }

        }

        forgetPass.setOnClickListener {
            val intent = Intent(this, ResetPassword1Activity::class.java)
            startActivity(intent)
        }
    }
}