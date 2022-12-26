package com.example.foodapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodapp.adapters.CategoryMealsAdapter
import com.example.foodapp.databinding.ActivityCategoryMealsBinding
import com.example.foodapp.fragments.HomeFragment
import com.example.foodapp.pojo.MealsByCategory
import com.example.foodapp.viewModel.CategoryMealsViewModel
import com.example.foodapp.viewModel.MealViewModel

class CategoryMealsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryMealsBinding
    private lateinit var categoryName: String
    private lateinit var categoryMealsAdapter: CategoryMealsAdapter

    private lateinit var categoryMealsMvvm: CategoryMealsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        categoryMealsMvvm = ViewModelProvider(this).get(CategoryMealsViewModel::class.java)

        prepareRecyclerView()

        getCategoryInformationFromIntent()
        categoryMealsMvvm.getMealsByCategory(categoryName)
        observerMealsLiveData()


    }

    private fun prepareRecyclerView() {
        categoryMealsAdapter = CategoryMealsAdapter()
        binding.rvMeals.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = categoryMealsAdapter
        }
    }

    private fun observerMealsLiveData() {
        categoryMealsMvvm.observeMealsListLiveData().observe(this, Observer { mealsList ->
            binding.tvCategoryCount.text = "${categoryName}: ${mealsList.size.toString()}"
            categoryMealsAdapter.setMealsList(mealsList)
        })
    }

    private fun getCategoryInformationFromIntent() {
        val intent = intent
        categoryName = intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!
    }
}