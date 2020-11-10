package com.example.amova1


import com.example.amova1.tilePanel.TilePanel

fun TilePanel.clear(): Unit{
    for(x in 0 until widthInTiles)
        for(y in 0 until heightInTiles){
            setTile(x,y,null);

        }
}

/**
 * Extension method to [TilePanel] that draws the given [MatrixPattern].
 */
fun TilePanel.drawPattern(model: MatrixPattern?) {
    clear()
    model?.forEach { setTile(it.x, it.y, PatternElementTile()) }
}


    /**
     * Extension method used to improve (from a Kotlin consumer code's perspective) the [TilePanel]'s
     * public interface. It registers the given listener to be called whenever a tile is clicked.
     */
    fun TilePanel.setTileListener(listener: (xTile: Int, yTile: Int) -> Boolean) {
        setListener(object : OnTileTouchAdapter() {
            override fun onClick(xTile: Int, yTile: Int): Boolean {
                return listener(xTile, yTile)
            }
        })
    }

    /**
     * Extension method used to remove the current tile listener from the [TilePanel].
     */
    fun TilePanel.unsetTileListener() {
        setListener(null)
    }






