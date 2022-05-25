package com.example.goodbook.ui.forgotPassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.goodbook.GoodBookApplication
import com.example.goodbook.R
import com.example.goodbook.databinding.ActivityForgotPasswordBinding
import com.example.goodbook.databinding.ActivityNewPasswordBinding
import com.example.goodbook.ui.LoginActivity
import com.example.goodbook.ui.viewmodel.LoginViewModel
import com.example.goodbook.ui.viewmodel.LoginViewModelFactory
import com.google.android.material.snackbar.Snackbar

class NewPasswordActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels() {
        LoginViewModelFactory(
            (application as GoodBookApplication).database
                .goodBookDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        
    }

    fun successChangePass() {
        val intent: Intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun failLogin() {
        showSnackbar()
    }

    private fun showSnackbar(): Boolean {
        val squeezeText = "Thay đổi mật khẩu thất bại"
        Snackbar.make(
            findViewById(R.id.forgot_pass_layout),
            squeezeText,
            Snackbar.LENGTH_SHORT
        ).show()
        return true
    }
}