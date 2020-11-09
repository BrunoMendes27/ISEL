package com.example.amova1

import com.example.amova1.tilePanel.TilePanel

fun TilePanel.clear(): Unit{
    for(x in 0 until widthInTiles)
        for(y in 0 until heightInTiles){
            setTile(x,y,null);

        }
}

fun drawPattern(matrixView: TilePanel, model: MatrixPattern?) {
    matrixView.clear()
    model?.forEach {
        matrixView.setTile(it.x, it.y, PatternElementTile())
    }
}





