package com.shoawcase.nativelib

class NativeLib {

    /**
     * A native method that is implemented by the 'nativelib' native library,
     * which is packaged with this application.
     */
    external fun fibonacci(n: Int): Long

    companion object {
        init {
            System.loadLibrary("nativelib")
        }

        private val instance = NativeLib()

        fun fibonacci(n: Int): Long = instance.fibonacci(n)
    }
}