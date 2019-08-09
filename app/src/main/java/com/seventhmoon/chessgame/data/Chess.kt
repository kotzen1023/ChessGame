package com.seventhmoon.chessgame.data

abstract class Chess {
    var isShowed: Boolean = false
    var isBeenEaten: Boolean = false
    var kind: Boolean = false // false-> Black, true-> red
    var level: Int = 0

    var coordinateX: Int = 0
    var coordinateY: Int = 0


    fun show (): Boolean {
        return if (!isShowed) {
            isShowed = true
            true
        } else {
            false
        }
    }

    fun moveUp(): Boolean {
        return if (coordinateY < 7) {

            coordinateY++
            true
        } else {
            false
        }
    }

    fun moveDown(): Boolean {
        return if (coordinateY == 0) {
            false
        } else {
            coordinateY--
            true
        }
    }

    fun moveRight(): Boolean {
        return if (coordinateX < 3) {
            coordinateX++
            true
        } else {
            false
        }
    }

    fun moveLeft(): Boolean {
        return if (coordinateX == 0) {
            false
        } else {
            coordinateX--
            true
        }
    }
}