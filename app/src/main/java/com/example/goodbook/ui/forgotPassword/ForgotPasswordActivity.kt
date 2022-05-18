package com.example.goodbook.ui.forgotPassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.goodbook.R
import com.example.goodbook.ui.LoginActivity

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
    }

    fun confirmAction(view: View) {
        val intent: Intent = Intent(this, NewPasswordActivity::class.java)
        startActivity(intent)
    }
    fun backAction(view: View) {
        var intent: Intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}