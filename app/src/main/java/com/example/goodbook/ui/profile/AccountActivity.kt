package com.example.goodbook.ui.profile

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.goodbook.R
import com.example.goodbook.data.GoodBookDatabase
import com.example.goodbook.model.User


class AccountActivity : AppCompatActivity(), View.OnClickListener {
    private var userDB: GoodBookDatabase?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val changePhone: ImageView = findViewById<View>(R.id.change_phone_btn) as ImageView
        val changeName: ImageView = findViewById<View>(R.id.change_name_btn) as ImageView
        val changeEmail: ImageView = findViewById<View>(R.id.change_email_btn) as ImageView
        val changePassword: ImageView = findViewById<View>(R.id.change_password_btn) as ImageView
        val closeEditNameFieldBtn: ImageView = findViewById<View>(R.id.change_name_clicked_btn) as ImageView
        val backBtn: ImageView = findViewById(R.id.back_btn)
        changePhone.setOnClickListener(this)
        changeName.setOnClickListener(this)
        changeEmail.setOnClickListener(this)
        changePassword.setOnClickListener(this)

        val inputName: EditText = findViewById(R.id.inputName)
        val saveBtn: TextView = findViewById(R.id.saveBtn)
        val fullName = intent.getStringExtra("userFullName")
        val name: TextView = findViewById(R.id.value_name)
        name.setText(fullName)
        saveBtn.setOnClickListener {
            val strName: String = inputName.text.toString()
        }


        closeEditNameFieldBtn.setOnClickListener(this)
        backBtn.setOnClickListener {
            finish()
        }
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val user_fullname = activity?.intent?.extras?.getString("userFullName")
//        val user_avt = activity?.intent?.extras?.getString("userAvt")
//        val userId = activity?.intent?.extras?.getInt("userId")
//
//        val name = view.findViewById<View>(R.id.name) as TextView
//        name.setText(user_fullname)
//    }

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

