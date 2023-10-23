package com.example.sportsinteractiveassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.example.sportsinteractiveassignment.R
import com.example.sportsinteractiveassignment.databinding.ActivityMainBinding
import com.example.sportsinteractiveassignment.viewmodel.MatchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    lateinit var  binding : ActivityMainBinding
    private val weatherViewModel by viewModel<MatchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


    }
}