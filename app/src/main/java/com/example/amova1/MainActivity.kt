package com.example.amova1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.view.postDelayed
import com.example.amova1.tilePanel.TilePanel
import kotlinx.android.synthetic.main.activity_main.*

private fun View.postDelayed(delay : Long,action : Runnable){
    this.postDelayed(action,delay)

}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ToGuess = MatrixPattern.fromRandom(8,MatrixView.widthInTiles)
        val current = MatrixPattern.empty(MatrixView.widthInTiles)
        Log.v("MemoryMatrix","onCreate on thread ${Thread.currentThread().name}")



        StartButton.setOnClickListener{
            Log.v("MemoryMatrix","onCreate on thread ${Thread.currentThread().name}")

            drawPattern(MatrixView,ToGuess)


            StartButton.postDelayed(10000) {
                Log.v("MemoryMatrix","postdelayed on thread ${Thread.currentThread().name}")
                drawPattern(MatrixView,current)
            }
        }

    }


}

