package com.showcase.movies.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.showcase.App
import com.showcase.R
import com.showcase.movies.Movie
import com.squareup.picasso.Picasso
import java.util.Random

class MovieActivity : AppCompatActivity() {
    private val id by lazy { intent.getStringExtra(KEY_ID)!! }
    private val movie by lazy { App.database.movieQueries.get(id).executeAsOne() }
    private val random by lazy { Random() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_activity)
        findViewById<TextView>(R.id.title).text = movie.title
        Picasso.get().load(movie.image).into(findViewById<ImageView>(R.id.image))
        findViewById<View>(R.id.delete).setOnClickListener {
            App.database.movieQueries.delete(id)
            finish()
        }
        findViewById<View>(R.id.duplicate).setOnClickListener {
            val suffix = random.nextInt(100)
            App.database.movieQueries.insertSingle(
                id + suffix,
                movie.title + suffix,
                movie.image
            )
        }
    }

    companion object {
        const val KEY_ID = "id"
        fun start(activity: Activity, movie: Movie) {
            activity.startActivity(Intent(activity, MovieActivity::class.java).apply {
                putExtra(KEY_ID, movie.id)
            })
        }
    }
}