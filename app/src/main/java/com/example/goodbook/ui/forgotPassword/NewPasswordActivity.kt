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
import com.google.android.material.textfield.TextInputEditText

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

        binding.buttonConfirmNewPW.setOnClickListener {
            val mail_phone = intent?.extras?.getString("mail_phone")
            val newPass = findViewById<TextInputEditText>(R.id.passPassInput).text.toString()
            val newPass1 = findViewById<TextInputEditText>(R.id.confirmNewPass).text.toString()

            if (check2PassisSame(newPass, newPass1)) {
                savePass(mail_phone!!, newPass)
            }
            else {
                failChange()
            }
        }
    }

    private fun successChangePass() {
        val intent: Intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun failChange() {
        showSnackbar()
    }

    private fun savePass(mail: String, pass: String) {
        val user = viewModel.getUserByMailPhone(mail)
        user.observe(this) {
            it.password = pass
            viewModel.updateUser(it)
        }
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

    private fun check2PassisSame(pass1: String, pass2: String): Boolean {
        return pass1 == pass2
    }
}