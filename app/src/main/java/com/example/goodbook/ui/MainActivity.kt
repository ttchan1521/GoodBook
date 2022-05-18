package com.example.goodbook.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.goodbook.R
import com.example.goodbook.ui.forgotPassword.ForgotPasswordActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val textview: TextView = findViewById(R.id.text4Login)
//        textview.setOnClickListener {
//            @Override
//            fun onClick(view: View) {
//                forgotPasswordAction(view)
//            }
//            Toast.makeText(this, "faile", Toast.LENGTH_SHORT);
//        }

    }
}