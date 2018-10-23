package com.example.oparinad.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button

var startTimer:Boolean = false
val splashScreenDuration:Long = 2000
var pressBackButton:Boolean = false
var secCounter:Long = 0

class MainActivity : AppCompatActivity() {
    //
    lateinit var button : Button
    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, AnotherActivity::class.java)

        //button= Button(this, R.id.action_text)

        Handler().postDelayed(
                {
                    if(!pressBackButton)
                        startActivity(intent)
                    pressBackButton = false
                    finish()
                },
                splashScreenDuration
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        pressBackButton = true
    }

}
