package com.showcase.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.showcase.R
import com.showcase.fibonacci.FibonacciActivity
import com.showcase.movies.list.MoviesListActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        fun <T : Activity> Int.onClickStartActivity(activity: Class<T>) {
            findViewById<View>(this).setOnClickListener {
                startActivity(
                    Intent(
                        this@MainActivity,
                        activity,
                    )
                )
            }
        }
        R.id.movies.onClickStartActivity(MoviesListActivity::class.java)
        R.id.fibonacci.onClickStartActivity(FibonacciActivity::class.java)
    }
}
