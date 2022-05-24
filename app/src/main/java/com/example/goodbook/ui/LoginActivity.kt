package com.example.goodbook.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.content.ContentValues.TAG
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import com.example.goodbook.GoodBookApplication
import com.example.goodbook.R
import com.example.goodbook.databinding.ActivityLoginBinding
import com.example.goodbook.model.User
import com.example.goodbook.ui.forgotPassword.ForgotPasswordActivity
import com.example.goodbook.ui.viewmodel.LoginViewModel
import com.example.goodbook.ui.viewmodel.LoginViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels() {
        LoginViewModelFactory(
            (application as GoodBookApplication).database
                .goodBookDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val allusers = viewModel.allUsers.observe(this) {
            Log.d(TAG, "viewModel.allUsers.observe is called")
        }
    }

    fun forgotPasswordAction(view: View){
        val intent: Intent = Intent(this, ForgotPasswordActivity::class.java)
        startActivity(intent)
    }

    fun registerAction(view: View){
        val intent: Intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun checkLogin(view: View) {
        val emailOrPhoneNumberInput = this.findViewById<TextInputEditText>(R.id.textFieldMailLoginInput).text
        val passwordInput = this.findViewById<TextInputEditText>(R.id.textPassLoginInput).text

        if (emailOrPhoneNumberInput != null && passwordInput != null) {
            lifecycleScope.launch {
                val user = viewModel.getUserByMailOrPhone(emailOrPhoneNumberInput.toString(), passwordInput.toString())
                    .observe(this@LoginActivity) {
                    if (it != null) {
                        successLogin(it)
                    }
                    else {
                        failLogin()
                    }
                }
            }
        }

    }

    fun successLogin(user: User?) {
        val intent: Intent = Intent(this, MainActivity::class.java)

        // lấy user vừa đăng nhập thành công ném vô đây
        val userAvt = user?.avt
        val userId = user?.userId
        val userFullname = user?.name

        Log.d(TAG, "successLogin:  + ${user?.userId}")

        intent.putExtra("userFullName", userFullname)
        intent.putExtra("userAvt", userAvt)
        intent.putExtra("userId", userId)

        startActivity(intent)
    }

    fun failLogin() {
        showSnackbar()
    }

    private fun showSnackbar(): Boolean {
        val squeezeText = "Đăng nhập thất bại"
        Snackbar.make(
            findViewById(R.id.loginConstraitLayout),
            squeezeText,
            Snackbar.LENGTH_SHORT
        ).show()
        return true
    }

}