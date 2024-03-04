package com.showcase.movies

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface MoviesService {
    @GET("movies.json")
    suspend fun list(): Response<ResponseBody>
}