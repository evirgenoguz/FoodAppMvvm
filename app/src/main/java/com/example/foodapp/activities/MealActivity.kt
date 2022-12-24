package com.example.foodapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodapp.databinding.ActivityMealBinding

class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}