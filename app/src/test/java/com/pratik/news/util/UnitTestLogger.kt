package com.pratik.news.util

class UnitTestLogger : Logger() {

    override fun e(tag: String, message: String) {
        print("E $tag $message")
    }

    override fun d(tag: String, message: String) {
        print("D $tag $message")
    }

    override fun w(tag: String, message: String) {
        print("W $tag $message")
    }

    override fun e(tag: String, message: String, throwable: Throwable) {
        print("E $tag $message ${throwable.localizedMessage}")
    }
}
