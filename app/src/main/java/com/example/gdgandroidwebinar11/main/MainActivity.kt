package com.example.gdgandroidwebinar11.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.gdgandroidwebinar11.AppViewModelFactory
import com.example.gdgandroidwebinar11.R
import com.example.gdgandroidwebinar11.data.NotesService

class MainActivity : AppCompatActivity() {

    //private val viewModel: MainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    private val viewModel: MainViewModel by viewModels { AppViewModelFactory(NotesService) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}