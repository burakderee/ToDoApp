package com.example.todoapp.features.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.local.CategoryEntity
import com.example.todoapp.ui.main.MainActivity
import com.example.todoapp.ui.task.HomeViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import com.example.todoapp.ui.task.EditTaskDialogFragment
import com.example.todoapp.ui.task.AddCategoryDialogFragment

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var recyclerViewCategories: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupUI()
        setupObservers()
    }

    private fun setupUI() {
        recyclerViewCategories = findViewById(R.id.recyclerViewCategories)

        categoryAdapter = CategoryAdapter { category ->
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("CATEGORY_KEY", category.categoryName)
            startActivity(intent)
        }
        val fabAddCategory: FloatingActionButton = findViewById(R.id.fabAddCategory)
        fabAddCategory.setOnClickListener {
            AddCategoryDialogFragment().show(supportFragmentManager, "AddCategoryDialog")
        }

        recyclerViewCategories.adapter = categoryAdapter
        recyclerViewCategories.layoutManager = LinearLayoutManager(this)
    }

    private fun setupObservers() {
        viewModel.allCategories.observe(this) { categories: List<CategoryEntity> ->
            categoryAdapter.submitList(categories)
        }
    }
}