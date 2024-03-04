package com.showcase.movies.list

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.showcase.App
import com.showcase.movies.MoviesRepository
import kotlinx.coroutines.launch

class MoviesListActivity : AppCompatActivity() {
    private val viewModel: MoviesListActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.movies.collect {

            }
        }
        viewModel.onCreate()
    }
}