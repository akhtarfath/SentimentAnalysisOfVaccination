package com.aplikasikaryaanakbangkit.sentiment.utils

import android.os.Handler
import android.os.Looper
import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors @VisibleForTesting constructor(
    private val _diskIO: Executor,
    private val _networkIO: Executor,
    private val _mainThread: Executor
) {

    companion object {
        private const val THREAD_COUNT = 3
    }

    constructor() : this(
        Executors.newSingleThreadExecutor(),
        Executors.newFixedThreadPool(THREAD_COUNT),
        MainThreadExecutor()
    )

    fun diskIO(): Executor = _diskIO

    fun networkIO(): Executor = _networkIO

    fun mainThread(): Executor = _mainThread

    private class MainThreadExecutor : Executor {
        private val _mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            _mainThreadHandler.post(command)
        }
    }
}