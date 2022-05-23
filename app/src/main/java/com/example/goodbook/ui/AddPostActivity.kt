package com.example.goodbook.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.goodbook.GoodBookApplication
import com.example.goodbook.R
import com.example.goodbook.databinding.ActivityAddPostBinding
import com.example.goodbook.ui.viewmodel.CategoryModel
import com.example.goodbook.ui.viewmodel.CategoryViewModelFactory


class AddPostActivity : AppCompatActivity() {
    private val viewModel: CategoryModel by viewModels() {
        CategoryViewModelFactory(
            (application as GoodBookApplication).database.
            goodBookDao()
        )
    }
    private val category = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            finish()
        }


        viewModel.allCategories.observe(this, Observer { categories ->
            for (cate in categories) {
                category.add(cate.type)
            }

        })



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



    }
}