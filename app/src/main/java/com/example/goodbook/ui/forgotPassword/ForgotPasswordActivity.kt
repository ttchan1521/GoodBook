package com.example.goodbook.ui.forgotPassword

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.goodbook.GoodBookApplication
import com.example.goodbook.R
import com.example.goodbook.databinding.ActivityForgotPasswordBinding
import com.example.goodbook.databinding.ActivityLoginBinding
import com.example.goodbook.model.User
import com.example.goodbook.ui.LoginActivity
import com.example.goodbook.ui.MainActivity
import com.example.goodbook.ui.RegisterActivity
import com.example.goodbook.ui.viewmodel.LoginViewModel
import com.example.goodbook.ui.viewmodel.LoginViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class ForgotPasswordActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels() {
        LoginViewModelFactory(
            (application as GoodBookApplication).database
                .goodBookDao()
        )
    }

    private lateinit var mail_phone_input: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val allusers = viewModel.allUsers.observe(this) {
            Log.d(ContentValues.TAG, "viewModel.allUsers.observe is called")
        }
    }

    fun confirmAction(view: View) {
        val intent: Intent = Intent(this, NewPasswordActivity::class.java)
        mail_phone_input = findViewById<TextInputEditText>(R.id.TextInputEditTextPass).text.toString()
        intent.putExtra("mail_phone", mail_phone_input)
        startActivity(intent)
    }
    fun backAction(view: View) {
        var intent: Intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}