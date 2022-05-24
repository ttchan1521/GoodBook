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
                if (viewModel.checkLoginUser(emailOrPhoneNumberInput.toString(), passwordInput.toString())) {
                    val user: LiveData<User>? =
                        viewModel.getUserByMailOrPhone(emailOrPhoneNumberInput.toString())
                    successLogin(user)
                } else {
                    failLogin()
                }
            }
        }

    }

    fun successLogin(user: LiveData<User>?) {
        val intent: Intent = Intent(this, MainActivity::class.java)

        Log.d(TAG, "successLogin: " + "ok")

        // lấy user vừa đăng nhập thành công ném vô đây
        val userAvt = user?.value?.avt
        val userId = user?.value?.userId
        val userFullname = user?.value?.name

        intent.putExtra("userFullName", userFullname)
        intent.putExtra("userAvt", userAvt)
        intent.putExtra("userId", userId)

        startActivity(intent)
    }

    fun failLogin() {

    }

}