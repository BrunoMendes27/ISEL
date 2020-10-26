package com.example.amova1.Demo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.amova1.MARGIN

class DemoView(ctx : Context,attrs : AttributeSet?) : View(ctx,attrs) {

    var model: DemoModel? = null

    set(value) {
        field = value
        invalidate()

    }


    private val brush: Paint = Paint().apply {
        color = Color.parseColor("#AA8BC2")
        strokeWidth = 5f
        style = Paint.Style.FILL_AND_STROKE
    }



    override fun onDraw(canvas: Canvas?) {
        val localmodel : DemoModel? = model
            if(localmodel != null){

                canvas?.drawLine(localmodel.start.x, localmodel.start.y,localmodel.end.x,localmodel.end.y,brush)
            }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}