package com.showcase.movies.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.showcase.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody

class MoviesListActivityViewModel : ViewModel() {
    fun load() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val responseBody = App.moviesService.list().body() as ResponseBody
                App.database.movieQueries.insert(responseBody.string())
            }
        }
    }

    fun movies() = App
        .database
        .movieQueries
        .getAll()
        .asFlow()
        .mapToList(Dispatchers.IO)
}