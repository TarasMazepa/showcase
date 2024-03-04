package com.showcase.movies

import com.showcase.App
import okhttp3.ResponseBody

object MoviesRepository {
    suspend fun loadMoviesToDatabase() {
        val responseBody = App.moviesService.list().body() as ResponseBody
        App.database.movieQueries.insert(responseBody.string())
    }
}