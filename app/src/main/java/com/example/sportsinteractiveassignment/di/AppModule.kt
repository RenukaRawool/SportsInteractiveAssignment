package com.example.sportsinteractiveassignment.di

import com.example.sportsinteractiveassignment.data.Repository
import com.example.sportsinteractiveassignment.data.model.MatchModel
import com.example.sportsinteractiveassignment.utils.NetworkManager
import com.example.sportsinteractiveassignment.viewmodel.MatchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{

    single { Repository(NetworkManager(androidContext())) }
    viewModel { MatchViewModel(get()) }
}