package com.example.goodbook.ui.profile

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.goodbook.R
import java.io.IOException
import java.io.InputStream


class AccountActivity : AppCompatActivity(), View.OnClickListener {

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
            R.id.change_password_btn -> startActivity(Intent(this, PasswordAuthentication::class.java))
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

