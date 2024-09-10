package com.example.harry_potter_and_retrofit.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.harry_potter_and_retrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


    }
}