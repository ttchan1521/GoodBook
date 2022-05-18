package com.example.goodbook.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.goodbook.R
import com.example.goodbook.databinding.ActivityLoginBinding
import com.example.goodbook.ui.forgotPassword.ForgotPasswordActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun forgotPasswordAction(view: View){
        var intent: Intent = Intent(this, ForgotPasswordActivity::class.java)
        startActivity(intent)
    }

    fun registerAction(view: View){
        var intent: Intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

}