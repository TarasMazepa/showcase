package com.showcase.fibonacci

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.showcase.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FibonacciActivity : AppCompatActivity() {
    private val viewModel: FibonacciActivityViewModel by viewModels()
    private val nView: EditText by lazy { findViewById<EditText>(R.id.n) }
    private val fibonacciView: TextView by lazy { findViewById<TextView>(R.id.fibonacci) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fibonacci_activity)
        lifecycleScope.launch {
            viewModel.fibonacci.collectLatest {
                fibonacciView.text = "$it"
            }
        }
        lifecycleScope.launch {
            viewModel.n.collectLatest {
                nView.setText("$it")
            }
        }
        nView.doAfterTextChanged {
            viewModel.onNChanged(it)
        }
        findViewById<View>(R.id.reset).setOnClickListener {
            viewModel.onReset()
        }
        findViewById<View>(R.id.next).setOnClickListener {
            viewModel.onNext()
        }
    }
}