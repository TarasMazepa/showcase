package com.showcase

import android.app.Application
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.showcase.movies.MoviesService
import retrofit2.Retrofit

class App : Application() {
    companion object {
        lateinit var app: App
        private val retrofit: Retrofit by lazy {
            Retrofit
                .Builder()
                .baseUrl("http://eng-assets.s3-website-us-west-2.amazonaws.com/fixture/")
                .build()
        }
        val moviesService: MoviesService by lazy {
            retrofit.create(MoviesService::class.java)
        }
        private val androidSqliteDriver by lazy {
            AndroidSqliteDriver(Database.Schema, app)
        }
        val database by lazy {
            Database(androidSqliteDriver)
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }
}