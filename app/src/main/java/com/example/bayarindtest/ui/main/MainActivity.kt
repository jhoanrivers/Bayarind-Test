package com.example.bayarindtest.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bayarindtest.R
import com.example.bayarindtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }
}