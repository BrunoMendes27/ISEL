package com.example.amova1.Demo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent.ACTION_DOWN
import android.view.KeyEvent.ACTION_UP
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_MOVE
import android.view.View
import com.example.amova1.R

class DemoActivity : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
        var initPoint = Point(0f,0f)
        findViewById<DemoView>(R.id.demoView).setOnTouchListener { v, event ->
            when(event.action){
                ACTION_DOWN -> initPoint = Point(event.x,event.y)
                ACTION_UP -> {
                    val demoView = findViewById<DemoView>(R.id.demoView)
                    demoView.model = DemoModel(initPoint,Point(event.x,event.y))

                }

            }
            true

        }
    }
}
