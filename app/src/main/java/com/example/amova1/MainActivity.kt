package com.example.amova1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.view.postDelayed
import com.example.amova1.databinding.ActivityMainBinding
import com.example.amova1.tilePanel.OnTileTouchListener
import com.example.amova1.tilePanel.TilePanel
import kotlinx.android.synthetic.main.activity_main.*

private fun View.postDelayed(delay : Long,action : Runnable){
    this.postDelayed(action,delay)

}

private open class OnTileTouchAdapter: OnTileTouchListener {

    override fun onClick(xTile: Int, yTile: Int)=false

    override fun onDrag(xFrom: Int, yFrom: Int, xTo: Int, yTo: Int)=false

    override fun onDragEnd(x: Int, y: Int){}

    override fun onDragCancel() {}

}

private const val PATTERN_SIZE = 8

class MainActivity : AppCompatActivity() {

        private fun startGame(binding: ActivityMainBinding, ToGuess:MatrixPattern){

            var current = MatrixPattern.empty(binding.MatrixView.widthInTiles)
            drawPattern(binding.MatrixView,current)

            binding.MatrixView.setListener(object : OnTileTouchAdapter(){
                override fun onClick(xTile: Int, yTile: Int): Boolean {
                    current += Position(xTile,yTile)
                    drawPattern(binding.MatrixView,current)
                    if(current.count == ToGuess.count){
                        endGame(binding,ToGuess)
                    }

                    return true
                }

            })

        }

        private fun endGame(binding: ActivityMainBinding, ToGuess:MatrixPattern){
            binding.MatrixView.setListener(null)
            binding.StartButton.postDelayed(5000){
                binding.StartButton.isEnabled = true
                drawPattern(binding.MatrixView,ToGuess)
            }

        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            val binding = ActivityMainBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)

        binding.StartButton.setOnClickListener{

            val ToGuess = MatrixPattern.fromRandom(8,binding.MatrixView.widthInTiles)
            drawPattern(binding.MatrixView,ToGuess)


            binding.StartButton.postDelayed(10000) {
                startGame(binding,ToGuess)


            }
        }

    }


}

