package com.showcase.movies.list

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.showcase.App
import com.showcase.R
import com.showcase.movies.details.MovieActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MoviesListActivity : AppCompatActivity() {
    private val viewModel: MoviesListActivityViewModel by viewModels()
    private val moviesAdapter by lazy {
        MoviesAdapter {
            MovieActivity.start(this, it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movies_list_activity)
        val recyclerView = findViewById<RecyclerView>(R.id.list)
        recyclerView.adapter = moviesAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.load()
        lifecycleScope.launch {
            viewModel.movies().collectLatest {
                moviesAdapter.items = it
            }
        }
        findViewById<View>(R.id.delete).setOnClickListener {
            App.database.movieQueries.deleteAll()
        }
        findViewById<View>(R.id.fetch).setOnClickListener {
            viewModel.load()
        }
    }
}