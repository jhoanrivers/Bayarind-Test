package com.example.bayarindtest.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.GridLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bayarindtest.R
import com.example.bayarindtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var possiblyAdapter: PossiblyPayAdapter

    private val mainViewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        bindFields()
        bindViewModel()
        bindViewEvents()

    }


    private fun  initView() {
        possiblyAdapter = PossiblyPayAdapter()
        binding.rvResults.apply {
            adapter = possiblyAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 3)
        }

    }

    private fun bindFields() {
        binding.etAmount.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val text = binding.etAmount.text.toString()
                if(text.isNotEmpty()){
                    mainViewModel.findPossibly(text)
                }
            }

        })
    }


    private fun bindViewEvents() {
    }

    private fun bindViewModel() {

        mainViewModel.possiblyPay.observe(this){
            possiblyAdapter.updatePossiblyPays(it)
        }

        mainViewModel.isLoading.observe(this){
            if(it){
                binding.pb.visibility = View.VISIBLE
            } else{
                binding.pb.visibility = View.GONE
            }
        }


    }



}