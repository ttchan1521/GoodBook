package com.example.goodbook.ui

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.goodbook.GoodBookApplication
import com.example.goodbook.R
import com.example.goodbook.databinding.ActivityAddPostBinding
import com.example.goodbook.ui.viewmodel.CategoryModel
import com.example.goodbook.ui.viewmodel.CategoryViewModelFactory
import com.example.goodbook.ui.viewmodel.PostModel
import com.example.goodbook.ui.viewmodel.PostViewModelFactory


class AddPostActivity : AppCompatActivity() {
    private val viewModel: CategoryModel by viewModels() {
        CategoryViewModelFactory(
            (application as GoodBookApplication).database.
            goodBookDao()
        )
    }
    private val postModel: PostModel by viewModels() {
        PostViewModelFactory(
            (application as GoodBookApplication).database.postDao()
        )
    }

    private val category = listOf<String>("Chính trị - Pháp luật", "Khoa học công nghệ - kinh tế", "Văn học - Nghệ thuật", "Văn hóa xã hội - Lịch sử",
                                            "Giáo trình", "Tâm lý- Tôn giáo")

    var pickedPhoto: Uri? = null
    var pickedBitmap : Bitmap? = null

    lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageView = binding.sourceImg
        binding.backBtn.setOnClickListener {
            finish()
        }


        val spinner = binding.listCategory
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, category
            )
            spinner.adapter = adapter
        }


        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                Log.d("Trang", "tt")
                //Toast.makeText(this@AddPostActivity, category[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }


        binding.addImg.setOnClickListener {
            pickedPhoto()
        }

        val userId = intent?.extras?.getInt("userId")
        binding.done.setOnClickListener {
            postModel.addNewPost(
                binding.title.text.toString(),
                pickedBitmap,
                binding.author.text.toString(),
                binding.description.text.toString(),
                userId!!, spinner.selectedItemPosition - 1
            )
            Toast.makeText(this@AddPostActivity, "Đăng thành công", Toast.LENGTH_SHORT).show()
            finish()
        }

    }

    fun pickedPhoto() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        } else {
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, 2)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, 2)
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            Log.d("Trang", "tt1")
            pickedPhoto = data.data
            if (Build.VERSION.SDK_INT >= 28) {
                val source = ImageDecoder.createSource(this.contentResolver, pickedPhoto!!)
                pickedBitmap = ImageDecoder.decodeBitmap(source)
                imageView.setImageBitmap(pickedBitmap)
                Log.d("Trang", "tt")
            } else {
                pickedBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, pickedPhoto)
                imageView.setImageBitmap(pickedBitmap)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}