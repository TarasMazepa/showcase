package com.showcase.fibonacci

import android.text.Editable
import androidx.lifecycle.ViewModel
import com.shoawcase.nativelib.NativeLib
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class FibonacciActivityViewModel : ViewModel() {
    private val _n = MutableStateFlow(0)
    val fibonacci: Flow<Long> = _n.map { NativeLib.fibonacci(it) }
    val n: Flow<Int> = _n

    fun onNChanged(it: Editable?) {
        _n.tryEmit(it.toString().toIntOrNull() ?: 0)
    }

    fun onReset() {
        _n.tryEmit(0)
    }

    fun onNext() {
        _n.tryEmit(++_n.value)
    }
}