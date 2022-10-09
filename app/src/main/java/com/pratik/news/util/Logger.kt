package com.pratik.news.util

import android.util.Log

open class Logger {

    open fun e(tag: String, message: String, throwable: Throwable) {
        Log.e(tag, message, throwable)
    }

    open fun w(tag: String, message: String) {
        Log.w(tag, message)
    }

    open fun v(tag: String, message: String) {
        Log.v(tag, message)
    }

    open fun d(tag: String, message: String) {
        Log.d(tag, message)
    }

    open fun e(tag: String, message: String) {
        Log.e(tag, message)
    }
}
