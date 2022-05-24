package com.example.goodbook.ui.profile
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import com.example.goodbook.GoodBookApplication
import com.example.goodbook.R
import com.example.goodbook.model.User
import com.example.goodbook.ui.viewmodel.LoginViewModel
import com.example.goodbook.ui.viewmodel.LoginViewModelFactory

class PasswordAuthentication : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModels() {
        LoginViewModelFactory(
            (application as GoodBookApplication).database
                .goodBookDao()
        )
    }

    lateinit var newPassword: EditText
    lateinit var comfirmPassword: EditText
    val MIN_PASSWORD_LENGTH = 6;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_authentication)
        supportActionBar!!.title = "Xác thực mật khẩu"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setBackgroundDrawable(resources.getDrawable(R.drawable.shape2))

        val resetPassBtn: Button = findViewById(R.id.next_to_change_password_btn)
        val forgetPass: TextView = findViewById(R.id.forget_pass)
        val oldPassword: EditText = findViewById(R.id.old_password)
        newPassword = findViewById(R.id.new_password)
        comfirmPassword = findViewById(R.id.confirm_password)

        val password = intent.getStringExtra("password")
        val fullName = intent.getStringExtra("userFullName")
        val email = intent.getStringExtra("email")
        val phone = intent.getStringExtra("phone")
        resetPassBtn.setOnClickListener {
            if (!oldPassword.text.toString().equals("123456")) {
                oldPassword.setError("Mật khẩu không chính xác")
            } else {
                if (validateInput()) {
                    val newpass = newPassword.text.toString()
                    viewModel.updateUser(User(name = fullName.toString(), password = newpass, email = email.toString(), phoneNumber = phone.toString()))
                    Toast.makeText(this,"Reset Password Success", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, AccountActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        forgetPass.setOnClickListener {
            val intent = Intent(this, ResetPassword1Activity::class.java)
            startActivity(intent)
        }
    }

    fun validateInput(): Boolean {
        if (newPassword.text.toString().equals("")) {
            newPassword.setError("Nhập mật khẩu mới")
            return false
        }
        if (newPassword.text.length <= MIN_PASSWORD_LENGTH) {
            newPassword.setError("Password phải nhiều hơn " + MIN_PASSWORD_LENGTH + " ký tự")
            return false
        }
        if (comfirmPassword.text.toString().equals("")) {
            comfirmPassword.setError("Vui lòng xác nhận lại password")
            return false
        }

        if (!newPassword.text.toString().equals(comfirmPassword.text.toString())) {
            comfirmPassword.setError("Mật khẩu không trùng khớp")
            return false
        }
        return true
    }
}