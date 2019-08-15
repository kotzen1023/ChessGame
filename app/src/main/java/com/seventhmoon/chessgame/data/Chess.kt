package com.seventhmoon.chessgame.data

abstract class Chess {
    var id: Int = 0
    var isShowed: Boolean = false
    var isDefeat: Boolean = false
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

    fun moveUp(boardRows: Int): Boolean {
        return if (coordinateY < boardRows - 1) {

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

    fun moveRight(boardColumns: Int): Boolean {
        return if (coordinateX < boardColumns - 1) {
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

    fun replace(opp_x: Int, opp_y: Int, opp_level: Int, opp_kind: Boolean, opp_show: Boolean, board: Board): Boolean {
        var ret = false

        if (this.isShowed && opp_show) { //must both been showed

            if (this.kind != opp_kind) { //kind is different
                if (this.level == 7) {//general
                    if (opp_level == 1) { //general, only  can't take soldier
                        ret = false
                    } else { //replace
                        if (isNeighbor(opp_x, opp_y)) {
                            this.coordinateX = opp_x
                            this.coordinateY = opp_y
                            ret = true
                        }
                    }
                } else if (this.level == 2) { //cannon, special
                    if (this.coordinateX != opp_x && this.coordinateY != opp_y) { //not the same row or column
                        ret = false
                    } else if (isNeighbor(opp_x, opp_y)) {
                        ret = false
                    } else {
                        if (isCannonTarget(opp_x, opp_y, board)) {
                            this.coordinateX = opp_x
                            this.coordinateY = opp_y
                            ret = true
                        }
                    }
                } else if (this.level == 1) { //soldier, can take down general and soldier
                    if (opp_level == 7 || opp_level == 1) {
                        if (isNeighbor(opp_x, opp_y)) { // only neighbor can be ate
                            this.coordinateX = opp_x
                            this.coordinateY = opp_y
                            ret = true
                        }
                    }
                } else { //follow the rule: low level can't eat high level, and only neighbor can be ate
                    if (this.level >= opp_level) {
                        this.coordinateX = opp_x
                        this.coordinateY = opp_y
                        ret = true
                    }
                }
            } else { //kind is the same
                ret = false
            }
        } else {
            ret = false
        }




        return ret
    }

    fun isNeighbor(opp_x: Int, opp_y: Int): Boolean {
        var ret = false

        val diffX = opp_x - this.coordinateX
        val diffY = opp_y - this.coordinateY

        val totalDiff = diffX + diffY

        if (totalDiff == 1 || totalDiff == -1) {
            ret = true
        }
        return ret
    }

    fun isCannonTarget(opp_x: Int, opp_y: Int, board: Board): Boolean {
        var ret = true
        if (this.coordinateX != opp_x && this.coordinateY != opp_y) {
            ret = false
        } else { //x or y is the same
            if (this.coordinateY == opp_y) {
                if (this.coordinateX < opp_x) { // find how many chess between us
                    var foundCount = 0
                    for(i in this.coordinateX+1 until opp_x - 1) {
                        val found = board.isAnyChessOnCoordinate(i, this.coordinateY)
                        if (found)
                            foundCount++
                    }

                    if (foundCount > 1) { // there are more than one chess between us, so you can't eat me
                        ret = false
                    }
                } else { //opp_x < this.coordinateX
                    var foundCount = 0
                    for(i in opp_x+1 until this.coordinateX - 1) {
                        val found = board.isAnyChessOnCoordinate(i, this.coordinateY)
                        if (found)
                            foundCount++
                    }

                    if (foundCount > 1) { // there are more than one chess between us, so you can't eat me
                        ret = false
                    }
                }
            } else { //this.coordinateX == opp_x
                if (this.coordinateY < opp_y) { // find how many chess between us
                    var foundCount = 0
                    for(i in this.coordinateY+1 until opp_y - 1) {
                        val found = board.isAnyChessOnCoordinate(this.coordinateX, i)
                        if (found)
                            foundCount++
                    }

                    if (foundCount > 1) { // there are more than one chess between us, so you can't eat me
                        ret = false
                    }
                } else { //opp_x < this.coordinateX
                    var foundCount = 0
                    for(i in opp_y+1 until this.coordinateY - 1) {
                        val found = board.isAnyChessOnCoordinate(this.coordinateX, i)
                        if (found)
                            foundCount++
                    }

                    if (foundCount > 1) { // there are more than one chess between us, so you can't eat me
                        ret = false
                    }
                }
            }
        }

        return ret
    }
}