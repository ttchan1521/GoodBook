package com.example.goodbook.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.goodbook.R
import com.example.goodbook.data.GoodBookDatabase
import com.example.goodbook.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RegisterActivity : AppCompatActivity(), CoroutineScope {
    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
    lateinit var etName: EditText
    lateinit var etEmail: EditText
    lateinit var etPassword:EditText
    lateinit var etRepeatPassword:EditText
    val MIN_PASSWORD_LENGTH = 6;

    private var userDB: GoodBookDatabase ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        viewInitializations()
        val registerBtn: Button = findViewById(R.id.buttonRegister)
        registerBtn.setOnClickListener {
            launch {
                performSignUp()
            }
        }
    }

    fun viewInitializations() {
        etName = findViewById(R.id.et_name)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        etRepeatPassword = findViewById(R.id.et_repeat_pass)
    }

    fun validateInput(): Boolean {
        if (etName.text.toString().equals("")) {
            etName.setError("Họ và tên không được để trống")
            return false
        }
        if (etEmail.text.toString().equals("")) {
            etEmail.setError("Email không được để trống")
            return false
        }
        if (!isEmailValid(etEmail.text.toString())) {
            etEmail.setError("Email không hợp lệ")
            return false
        }
        if (etPassword.text.toString().equals("")) {
            etPassword.setError("Password không được để trống")
            return false
        }
        if (etPassword.text.length < MIN_PASSWORD_LENGTH) {
            etPassword.setError("Password phải nhiều hơn " + MIN_PASSWORD_LENGTH + "characters")
            return false
        }
        if (etRepeatPassword.text.toString().equals("")) {
            etRepeatPassword.setError("Vui lòng xác nhận lại password")
            return false
        }

//        if (etPhone.text.length < 10 || etPhone.text.length > 11) {
//            etPhone.setError("Số điện thoại không hợp lệ")
//            return false
//        }

        // Checking if repeat password is same
        if (!etPassword.text.toString().equals(etRepeatPassword.text.toString())) {
            etRepeatPassword.setError("Mật khẩu không trùng khớp")
            return false
        }
        return true
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    suspend fun performSignUp () {
        if (validateInput()) {

            // Input is valid, here send data to your server

            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val repeatPassword = etRepeatPassword.text.toString()
            userDB?.goodBookDao()?.insert(User(name = name, password = password, email = email, phoneNumber = "", avt = ""))
            Toast.makeText(this,"Register Success",Toast.LENGTH_SHORT).show()
            finish()

        }
    }

    fun backAction(view: View) {
        var intent: Intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}