package com.example.goodbook.ui.profile

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.goodbook.GoodBookApplication
import com.example.goodbook.R
import com.example.goodbook.data.GoodBookDatabase
import com.example.goodbook.model.User
import com.example.goodbook.ui.viewmodel.LoginViewModel
import com.example.goodbook.ui.viewmodel.LoginViewModelFactory


class AccountActivity : AppCompatActivity(), View.OnClickListener {
    private val viewModel: LoginViewModel by viewModels() {
        LoginViewModelFactory(
            (application as GoodBookApplication).database
                .goodBookDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val changePhone: ImageView = findViewById<View>(R.id.change_phone_btn) as ImageView
        val changeName: ImageView = findViewById<View>(R.id.change_name_btn) as ImageView
        val changeEmail: ImageView = findViewById<View>(R.id.change_email_btn) as ImageView
        val changePassword: ImageView = findViewById<View>(R.id.change_password_btn) as ImageView
        val closeEditNameFieldBtn: ImageView = findViewById<View>(R.id.change_name_clicked_btn) as ImageView
        val backBtn: ImageView = findViewById(R.id.back_btn)
        val inputName: EditText = findViewById(R.id.inputName)
        val saveBtn: TextView = findViewById(R.id.saveBtn)
        val strName: String = inputName.text.toString()
        val item = findViewById<View>(R.id.editNameField)
        val openEditNameFieldBtn = findViewById<View>(R.id.change_name_btn)

        changePhone.setOnClickListener(this)
        changeName.setOnClickListener(this)
        changeEmail.setOnClickListener(this)
        changePassword.setOnClickListener(this)


        val fullName = intent.getStringExtra("userFullName")
        val email = intent.getStringExtra("email")
        val phone = intent.getStringExtra("phone")
        val password = intent.getStringExtra("password")

        val name: TextView = findViewById(R.id.value_name)
        name.setText(fullName)
        saveBtn.setOnClickListener {
            viewModel.updateUser(User(password = password.toString(), name = strName, phoneNumber = phone.toString(), email = email.toString()))
            name.setText(strName)
            item.visibility = View.GONE
            closeEditNameFieldBtn.visibility = View.GONE
            openEditNameFieldBtn.visibility = View.VISIBLE
        }

        closeEditNameFieldBtn.setOnClickListener(this)
        backBtn.setOnClickListener {
            finish()
        }
    }

    override fun onClick(view: View) {
        val item = findViewById<View>(R.id.editNameField)
        val openEditNameFieldBtn = findViewById<View>(R.id.change_name_btn)
        val closeEditNameFieldBtn = findViewById<View>(R.id.change_name_clicked_btn)
        when (view.id) {
            R.id.change_phone_btn -> startActivity(Intent(this,PhoneActivity::class.java))
            R.id.change_password_btn -> {
                val intent = Intent(this, PasswordAuthentication::class.java)
                val password = intent.getStringExtra("password")

                intent.putExtra("password", password)
                startActivity(intent)
            }
            R.id.change_name_btn -> {
                item.visibility = View.VISIBLE
                openEditNameFieldBtn.visibility = View.GONE
                closeEditNameFieldBtn.visibility = View.VISIBLE
            }
            R.id.change_name_clicked_btn -> {
                item.visibility = View.GONE
                closeEditNameFieldBtn.visibility = View.GONE
                openEditNameFieldBtn.visibility = View.VISIBLE
            }
        }
    }
}

