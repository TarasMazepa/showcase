package com.showcase.movies.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.showcase.App
import com.showcase.movies.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesListActivityViewModel : ViewModel() {
    val movies = App
        .database
        .movieQueries
        .getAll()
        .asFlow()
        .mapToList(Dispatchers.IO)

    fun onCreate() {
        viewModelScope.launch {
            MoviesRepository.loadMoviesToDatabase()
        }
    }
}