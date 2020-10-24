package com.example.amova1.Demo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.amova1.MARGIN

class DemoView(ctx : Context,attrs : AttributeSet?) : View(ctx,attrs) {


    private val brush: Paint = Paint().apply {
        color = Color.parseColor("#AA8BC2")
        strokeWidth = 5f
        style = Paint.Style.FILL_AND_STROKE
    }



    override fun onDraw(canvas: Canvas?) {

            canvas?.drawLine(0f, 0f,width.toFloat(),height.toFloat(),brush)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}