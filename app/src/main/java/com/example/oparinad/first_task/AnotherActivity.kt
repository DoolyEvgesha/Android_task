package com.example.oparinad.myapplication

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.net.sip.SipErrorCode.TIME_OUT
import android.content.Intent
import android.os.Handler
import android.support.v4.os.HandlerCompat.postDelayed
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.EditText


class AnotherActivity : AppCompatActivity() {

    val minNums = listOf("один", "два","три","четыре","пять", "шесть","семь",
            "восемь","девять")
    val decimalNums  = listOf("двадцать","тридцать","сорок","пятьдесят",
            "шестьдесят","семьдесят","восемьдессят","девяносто")
    val teenNums = listOf("десять", "одиннадцать ", "двеннадцать ", "тринадцать ",
            "четырнадцать ", "пятнадцать ", "шестнадцать ", "семнадцать ",
            "восемнадцать ", "девятнадцать ")
    val hundredNums = listOf("сто ", "двести ", "триста ", "четыреста ",
            "пятьсот ", "шестьсот ", "семьсот ", "восемьсот ", "девятьсот ")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.timer_layout)

        if(pressBackButton) finish()

        val textView: TextView = findViewById(R.id.textView)
        val button : Button = findViewById(R.id.default_activity_button)
        val timer = Counter(millisInFuture = 1000000, countDownInterval = 1000)
        button.text = "Start"

        button.setOnClickListener {
            if(startTimer) {
                textView.text=""
                startTimer=false
                secCounter=0
                button.text = "Start"
                timer.cancel()
            }
            else{
                startTimer = true
                button.text ="Stop"
                timer.start()
            }
        }
    }

    fun numbersToRusWords(secCounter:Long) : String{
        var result : String
        if (secCounter.toInt() == 0)
            result = "ноль"
        else if ((secCounter%100 /10).toInt() == 1){
            result = teenNums[(secCounter%10).toInt()]
        } else {
            result = hundredNums[(secCounter/100).toInt()]
            result = result.plus(decimalNums[((secCounter%100)/10).toInt()])
            result = result.plus(minNums[(secCounter%10).toInt()])
        }
        return result
    }

    inner class Counter(millisInFuture:Long, countDownInterval: Long):CountDownTimer(millisInFuture, countDownInterval){
        var textView : TextView = findViewById(R.id.textView)
        override fun onFinish() {
            textView.text="Stop"
        }


        override fun onTick(millisUntilFinished: Long) {
            textView.text = numbersToRusWords(secCounter)
            secCounter++
        }
    }

}


