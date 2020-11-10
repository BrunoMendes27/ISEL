package com.example.amova1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.core.view.postDelayed
import com.example.amova1.databinding.ActivityMainBinding
import com.example.amova1.tilePanel.OnTileTouchListener
import com.example.amova1.tilePanel.TilePanel
import kotlinx.android.synthetic.main.activity_main.*

private fun View.postDelayed(delay : Long,action : Runnable){
    this.postDelayed(action,delay)

}

internal open class OnTileTouchAdapter: OnTileTouchListener {

    override fun onClick(xTile: Int, yTile: Int)=false

    override fun onDrag(xFrom: Int, yFrom: Int, xTo: Int, yTo: Int)=false

    override fun onDragEnd(x: Int, y: Int){}

    override fun onDragCancel() {}

}

private const val PATTERN_SIZE = 8
private const val MATRIX_STATE = "MatrixViewModel"

class MainActivity : AppCompatActivity() {

        private fun drawToGuess(){

            viewModel.startGame(PATTERN_SIZE,binding.MatrixView.widthInTiles)
            binding.MatrixView.drawPattern(viewModel.toGuess)
            binding.StartButton.isEnabled = false
            binding.StartButton.setOnClickListener(null)

            binding.StartButton.postDelayed(5000){
                drawGuessing()
            }
        }

    /**
     * Displays the UI associated to the game's Guessing state, that is, the user is adding new
     * entries to his current guess.
     */
    private fun drawGuessing() {
        binding.MatrixView.drawPattern(viewModel.current)

        binding.StartButton.isEnabled = false
        binding.StartButton.setOnClickListener(null)
        binding.MatrixView.setTileListener{ xTile, yTile ->
            viewModel.addGuess(Position(xTile, yTile))
            if (!viewModel.IsgameOngoing()) drawHasEnded()
            else drawGuessing()
            true
        }
    }


        private fun drawNotStarted(){
            binding.StartButton.isEnabled = true
            binding.StartButton.setOnClickListener { drawToGuess() }
            binding.MatrixView.unsetTileListener()
    }

        private fun drawHasEnded() {
            binding.MatrixView.drawPattern(viewModel.current)
        binding.StartButton.isEnabled = true
        binding.StartButton.setOnClickListener { drawToGuess() }
        binding.MatrixView.setListener(null)
        binding.StartButton.postDelayed(5000) {
            binding.StartButton.isEnabled = true
            binding.MatrixView.drawPattern(viewModel.toGuess)
        }
    }

   private val viewModel: MatrixViewModel by viewModels()
  private val binding : ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)


            val view = binding.root
            setContentView(view)



            when {
                viewModel.IsgameOngoing() && viewModel.current?.count == 0 -> drawToGuess()
                viewModel.IsgameOngoing() -> drawGuessing()
                else -> drawNotStarted()
            }

        }



    }


