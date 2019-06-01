package net.novaclient.nova.util

import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

object AsyncThreading {
    private val INC_INT = AtomicInteger()
    private val EXECUTOR =
        Executors.newFixedThreadPool(100) { r -> Thread(r, "Multithreading-" + INC_INT.incrementAndGet()) }

    fun asyncRunnable(runnable: () -> Unit) = EXECUTOR.execute(runnable)
}