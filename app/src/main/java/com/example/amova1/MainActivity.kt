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

private open class OnTileTouchAdapter: OnTileTouchListener {

    override fun onClick(xTile: Int, yTile: Int)=false

    override fun onDrag(xFrom: Int, yFrom: Int, xTo: Int, yTo: Int)=false

    override fun onDragEnd(x: Int, y: Int){}

    override fun onDragCancel() {}

}

private const val PATTERN_SIZE = 8
private const val MATRIX_STATE = "MatrixViewModel"

class MainActivity : AppCompatActivity() {

        private fun drawToGuess(viewModel: MatrixViewModel,binding: ActivityMainBinding){

            viewModel.StartGame(PATTERN_SIZE,binding.MatrixView.widthInTiles)
            drawPattern(binding.MatrixView,viewModel.toGuess)
            binding.StartButton.isEnabled = false
            binding.StartButton.setOnClickListener(null)

            binding.StartButton.postDelayed(5000){
                drawCurrent(viewModel,binding)
            }
        }

         private fun drawCurrent(viewModel: MatrixViewModel, binding: ActivityMainBinding) {
        drawPattern(binding.MatrixView, viewModel.current)

        binding.StartButton.isEnabled = false
        binding.StartButton.setOnClickListener(null)
        binding.MatrixView.setListener(object : OnTileTouchAdapter() {
            override fun onClick(xTile: Int, yTile: Int): Boolean {
                viewModel.addGuess(Position(xTile, yTile))
                if (!viewModel.IsgameOngoing()) drawHasEnded(viewModel, binding)
                else drawCurrent(viewModel, binding)
                return true
            }
        })
    }


        private fun drawNotStarted(viewModel: MatrixViewModel,binding: ActivityMainBinding){
            drawPattern(binding.MatrixView,viewModel.current)
        binding.StartButton.isEnabled=true
        binding.StartButton.setOnClickListener{
            drawToGuess(viewModel,binding)
        }
            binding.MatrixView.setListener(null)
    }

        private fun drawHasEnded(viewModel: MatrixViewModel, binding: ActivityMainBinding) {
        drawPattern(binding.MatrixView, viewModel.current)
        binding.StartButton.isEnabled = true
        binding.StartButton.setOnClickListener { drawToGuess(viewModel, binding) }
        binding.MatrixView.setListener(null)
        binding.StartButton.postDelayed(5000) {
            binding.StartButton.isEnabled = true
            drawPattern(binding.MatrixView, viewModel.toGuess)
        }
    }

   private val viewModel: MatrixViewModel by viewModels()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            val binding = ActivityMainBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)



            if(viewModel.IsgameOngoing()) {
                if(viewModel.current?.count ==0)
                    drawToGuess(viewModel,binding)
                else drawCurrent(viewModel, binding)
            } else
                drawNotStarted(viewModel, binding)

        }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(MATRIX_STATE, viewModel.getState())
    }
    }


