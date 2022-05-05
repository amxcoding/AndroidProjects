package com.example.livedatademo

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private var milliSecondsLeft: Long = 0
    private lateinit var timer: CountDownTimer


    /**
     * For every LiveData object you should have private MutableLiveData, and public LiveData which
     * will be exposed to other apps.
     */
    private val _timerValue = MutableLiveData<Long>()
    private val _seconds = MutableLiveData<Int>()
    private val _finished = MutableLiveData<Boolean>()
    private val _stopped = MutableLiveData<Boolean>()

    init {
        _stopped.value = false
    }

    /**
     * You can make a new function that returns the livedata
     * or
     * you can make a new variable of the type livedata and sets its value to livedata
     */
    val stopped: LiveData<Boolean> get() = _stopped
    val timerValue: LiveData<Long> get() = _timerValue
    val finished: LiveData<Boolean> get() = _finished
    fun seconds(): LiveData<Int> {
        return _seconds
    }

    fun startTimer(milliSeconds: Long) {
        // anonymous class
        val SECOND = 1000
        _timerValue.value = milliSeconds + SECOND

        runTimer(_timerValue.value!!.toLong())
    }

    fun stopTimer() {
        timer.cancel()
        _stopped.value = true
    }

    fun resumeTimer() {
        runTimer(milliSecondsLeft)

        _stopped.value = false
    }

    private fun runTimer(milliSeconds: Long) {
        timer = object : CountDownTimer(milliSeconds, 1000) {
            override fun onTick(p0: Long) {
                milliSecondsLeft = p0
                val timeLeft = p0/1000
                _seconds.value = timeLeft.toInt()

            }

            override fun onFinish() {
                _finished.value = true

            }
        }.start()
    }
}