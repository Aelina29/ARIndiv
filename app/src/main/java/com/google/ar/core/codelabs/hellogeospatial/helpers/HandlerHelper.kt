package com.google.ar.core.codelabs.hellogeospatial.helpers

import android.os.Handler
import android.os.Looper

class HandlerHelper (val someMethod:()->Unit){
    private val handler = Handler(Looper.getMainLooper())
    private val delayMillis = 3000L // 3 секунды

    // Вызывается для запуска задачи
    fun startRepeatingTask() {
        handler.postDelayed({
            // Ваш код, который нужно выполнить каждые 3 секунды
            // Например:
            someMethod()

            // Повторяем задачу через указанное время
            startRepeatingTask()
        }, delayMillis)
    }

    // Вызывается для остановки задачи
    fun stopRepeatingTask() {
        handler.removeCallbacksAndMessages(null)
    }
}