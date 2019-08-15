package com.seventhmoon.chessgame.data

import android.util.Log


class Board(rows: Int, columns: Int) {
    private val mTAG = Board::class.java.name

    private var rows: Int = 0
    private var columns: Int = 0
    var boardArray: Array<IntArray>  //Array(rows) {Array(columns) {0}}
    val arrayList: ArrayList<Int> = ArrayList()

    var generalRed: General = General()
    var guardRed1: Guard = Guard()
    var guardRed2: Guard = Guard()
    var handRed1: Hand = Hand()
    private var handRed2: Hand = Hand()
    private var castleRed1: Castle = Castle()
    private var castleRed2: Castle = Castle()
    private var knightRed1: Knight = Knight()
    private var knightRed2: Knight = Knight()
    private var cannonRed1: Cannon = Cannon()
    private var cannonRed2: Cannon = Cannon()
    private var soldierRed1: Soldier = Soldier()
    private var soldierRed2: Soldier = Soldier()
    private var soldierRed3: Soldier = Soldier()
    private var soldierRed4: Soldier = Soldier()
    private var soldierRed5: Soldier = Soldier()

    private var generalBlack: General = General()
    private var guardBlack1: Guard = Guard()
    private var guardBlack2: Guard = Guard()
    private var handBlack1: Hand = Hand()
    private var handBlack2: Hand = Hand()
    private var castleBlack1: Castle = Castle()
    private var castleBlack2: Castle = Castle()
    private var knightBlack1: Knight = Knight()
    private var knightBlack2: Knight = Knight()
    private var cannonBlack1: Cannon = Cannon()
    private var cannonBlack2: Cannon = Cannon()
    private var soldierBlack1: Soldier = Soldier()
    private var soldierBlack2: Soldier = Soldier()
    private var soldierBlack3: Soldier = Soldier()
    private var soldierBlack4: Soldier = Soldier()
    private var soldierBlack5: Soldier = Soldier()

    init {
        this.rows = rows
        this.columns = columns
        this.boardArray = Array(rows) { IntArray(columns) }

        //init chess id
        generalRed.id = 1
        guardRed1.id = 2
        guardRed2.id = 3
        handRed1.id = 4
        handRed2.id = 5
        castleRed1.id = 6
        castleRed2.id = 7
        knightRed1.id = 8
        knightRed2.id = 9
        cannonRed1.id = 10
        cannonRed2.id = 11
        soldierRed1.id = 12
        soldierRed2.id = 13
        soldierRed3.id = 14
        soldierRed4.id = 15
        soldierRed5.id = 16

        generalRed.kind = true
        guardRed1.kind = true
        guardRed2.kind = true
        handRed1.kind = true
        handRed2.kind = true
        castleRed1.kind = true
        castleRed2.kind = true
        knightRed1.kind = true
        knightRed2.kind = true
        cannonRed1.kind = true
        cannonRed2.kind = true
        soldierRed1.kind = true
        soldierRed2.kind = true
        soldierRed3.kind = true
        soldierRed4.kind = true
        soldierRed5.kind = true

        generalBlack.id = 17
        guardBlack1.id = 18
        guardBlack2.id = 19
        handBlack1.id = 20
        handBlack2.id = 21
        castleBlack1.id = 22
        castleBlack2.id = 23
        knightBlack1.id = 24
        knightBlack2.id = 25
        cannonBlack1.id = 26
        cannonBlack2.id = 27
        soldierBlack1.id = 28
        soldierBlack2.id = 29
        soldierBlack3.id = 30
        soldierBlack4.id = 31
        soldierBlack5.id = 32


        arrayList.clear()

        for (i in 1 until 33) {
            arrayList.add(i)
        }

        arrayList.shuffle()

        Log.d(mTAG, "arrayList = $arrayList")

        var count = 0
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                this.boardArray[i][j] = arrayList[count]

                when(arrayList[count]) {
                    1 -> { generalRed.coordinateX = i
                        generalRed.coordinateY = j }
                    2 -> { guardRed1.coordinateX = i
                        guardRed1.coordinateY = j}
                    3 -> { guardRed2.coordinateX = i
                        guardRed2.coordinateY = j}
                    4 -> { handRed1.coordinateX = i
                        handRed1.coordinateY = j}
                    5 -> { handRed2.coordinateX = i
                        handRed2.coordinateY = j}
                    6 -> { castleRed1.coordinateX = i
                        castleRed1.coordinateY = j}
                    7 -> { castleRed2.coordinateX = i
                        castleRed2.coordinateY = j}
                    8 -> { knightRed1.coordinateX = i
                        knightRed1.coordinateY = j}
                    9 -> { knightRed2.coordinateX = i
                        knightRed2.coordinateY = j}
                    10-> { cannonRed1.coordinateX = i
                        cannonRed1.coordinateY = j}
                    11-> { cannonRed2.coordinateX = i
                        cannonRed2.coordinateY = j}
                    12-> { soldierRed1.coordinateX = i
                        soldierRed1.coordinateY = j}
                    13-> { soldierRed2.coordinateX = i
                        soldierRed2.coordinateY = j}
                    14-> { soldierRed3.coordinateX = i
                        soldierRed3.coordinateY = j}
                    15-> { soldierRed4.coordinateX = i
                        soldierRed4.coordinateY = j}
                    16-> { soldierRed5.coordinateX = i
                        soldierRed5.coordinateY = j}
                    17-> { generalBlack.coordinateX = i
                        generalBlack.coordinateY = j}
                    18-> { guardBlack1.coordinateX = i
                        guardBlack1.coordinateY = j}
                    19-> { guardBlack2.coordinateX = i
                        guardBlack2.coordinateY = j}
                    20-> { handBlack1.coordinateX = i
                        handBlack1.coordinateY = j}
                    21-> { handBlack2.coordinateX = i
                        handBlack2.coordinateY = j}
                    22-> { castleBlack1.coordinateX = i
                        castleBlack1.coordinateY = j}
                    23-> { castleBlack2.coordinateX = i
                        castleBlack2.coordinateY = j}
                    24-> { knightBlack1.coordinateX = i
                        knightBlack1.coordinateY = j}
                    25-> { knightBlack2.coordinateX = i
                        knightBlack2.coordinateY = j}
                    26-> { cannonBlack1.coordinateX = i
                        cannonBlack1.coordinateY = j}
                    27-> { cannonBlack2.coordinateX = i
                        cannonBlack2.coordinateY = j}
                    28-> { soldierBlack1.coordinateX = i
                        soldierBlack1.coordinateY = j}
                    29-> { soldierBlack2.coordinateX = i
                        soldierBlack2.coordinateY = j}
                    30-> { soldierBlack3.coordinateX = i
                        soldierBlack3.coordinateY = j}
                    31-> { soldierBlack4.coordinateX = i
                        soldierBlack4.coordinateY = j}
                    32-> { soldierBlack5.coordinateX = i
                        soldierBlack5.coordinateY = j}

                }

                count++
            }
        }

        for (i in 0 until rows) {
            for (j in 0 until columns) {
                print(boardArray[i][j])
                print(", ")
            }
            println()
        }



    }

    fun getArraySize(): Int {
        return arrayList.size
    }

    fun getArrayItem(index: Int): Int {
        //Log.e(mTAG, "get ${arrayList[index]}")
        return arrayList[index]
    }

    fun setArrayItem(index: Int, value: Int) {
        arrayList[index] = value
    }

    fun getRows(): Int {
        return  rows
    }

    fun setRows(rows: Int) {
        this.rows = rows
    }

    fun getColumns(): Int {
        return columns
    }

    fun setColumns(columns: Int) {
        this.columns = columns
    }



    fun resetShullfle() {
        arrayList.clear()

        for (i in 1 until 33) {
            arrayList.add(i)
        }

        arrayList.shuffle()

        Log.d(mTAG, "arrayList = $arrayList")

        var count = 0
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                this.boardArray[i][j] = arrayList[count]

                when(arrayList[count]) {
                    1 -> { generalRed.coordinateX = i
                        generalRed.coordinateY = j
                        generalRed.isShowed = false
                        generalRed.isDefeat = false}
                    2 -> { guardRed1.coordinateX = i
                        guardRed1.coordinateY = j
                        guardRed1.isShowed = false
                        guardRed1.isDefeat = false}
                    3 -> { guardRed2.coordinateX = i
                        guardRed2.coordinateY = j
                        guardRed2.isShowed = false
                        guardRed2.isDefeat = false}
                    4 -> { handRed1.coordinateX = i
                        handRed1.coordinateY = j
                        handRed1.isShowed = false
                        handRed1.isDefeat = false}
                    5 -> { handRed2.coordinateX = i
                        handRed2.coordinateY = j
                        handRed2.isShowed = false
                        handRed2.isDefeat = false}
                    6 -> { castleRed1.coordinateX = i
                        castleRed1.coordinateY = j
                        castleRed1.isShowed = false
                        castleRed1.isDefeat = false}
                    7 -> { castleRed2.coordinateX = i
                        castleRed2.coordinateY = j
                        castleRed2.isShowed = false
                        castleRed2.isDefeat = false}
                    8 -> { knightRed1.coordinateX = i
                        knightRed1.coordinateY = j
                        knightRed1.isShowed = false
                        knightRed1.isDefeat = false}
                    9 -> { knightRed2.coordinateX = i
                        knightRed2.coordinateY = j
                        knightRed2.isShowed = false
                        knightRed2.isDefeat = false}
                    10-> { cannonRed1.coordinateX = i
                        cannonRed1.coordinateY = j
                        cannonRed1.isShowed = false
                        cannonRed1.isDefeat = false}
                    11-> { cannonRed2.coordinateX = i
                        cannonRed2.coordinateY = j
                        cannonRed2.isShowed = false
                        cannonRed2.isDefeat = false}
                    12-> { soldierRed1.coordinateX = i
                        soldierRed1.coordinateY = j
                        soldierRed1.isShowed = false
                        soldierRed1.isDefeat = false}
                    13-> { soldierRed2.coordinateX = i
                        soldierRed2.coordinateY = j
                        soldierRed2.isShowed = false
                        soldierRed2.isDefeat = false}
                    14-> { soldierRed3.coordinateX = i
                        soldierRed3.coordinateY = j
                        soldierRed3.isShowed = false
                        soldierRed3.isDefeat = false}
                    15-> { soldierRed4.coordinateX = i
                        soldierRed4.coordinateY = j
                        soldierRed4.isShowed = false
                        soldierRed4.isDefeat = false}
                    16-> { soldierRed5.coordinateX = i
                        soldierRed5.coordinateY = j
                        soldierRed5.isShowed = false
                        soldierRed5.isDefeat = false}
                    17-> { generalBlack.coordinateX = i
                        generalBlack.coordinateY = j
                        generalBlack.isShowed = false
                        generalBlack.isDefeat = false}
                    18-> { guardBlack1.coordinateX = i
                        guardBlack1.coordinateY = j
                        guardBlack1.isShowed = false
                        guardBlack1.isDefeat = false}
                    19-> { guardBlack2.coordinateX = i
                        guardBlack2.coordinateY = j
                        guardBlack2.isShowed = false
                        guardBlack2.isDefeat = false}
                    20-> { handBlack1.coordinateX = i
                        handBlack1.coordinateY = j
                        handBlack1.isShowed = false
                        handBlack1.isDefeat = false}
                    21-> { handBlack2.coordinateX = i
                        handBlack2.coordinateY = j
                        handBlack2.isShowed = false
                        handBlack2.isDefeat = false}
                    22-> { castleBlack1.coordinateX = i
                        castleBlack1.coordinateY = j
                        castleBlack1.isShowed = false
                        castleBlack1.isDefeat = false}
                    23-> { castleBlack2.coordinateX = i
                        castleBlack2.coordinateY = j
                        castleBlack2.isShowed = false
                        castleBlack2.isDefeat = false}
                    24-> { knightBlack1.coordinateX = i
                        knightBlack1.coordinateY = j
                        knightBlack1.isShowed = false
                        knightBlack1.isDefeat = false}
                    25-> { knightBlack2.coordinateX = i
                        knightBlack2.coordinateY = j
                        knightBlack2.isShowed = false
                        knightBlack2.isDefeat = false}
                    26-> { cannonBlack1.coordinateX = i
                        cannonBlack1.coordinateY = j
                        cannonBlack1.isShowed = false
                        cannonBlack1.isDefeat = false}
                    27-> { cannonBlack2.coordinateX = i
                        cannonBlack2.coordinateY = j
                        cannonBlack2.isShowed = false
                        cannonBlack2.isDefeat = false}
                    28-> { soldierBlack1.coordinateX = i
                        soldierBlack1.coordinateY = j
                        soldierBlack1.isShowed = false
                        soldierBlack1.isDefeat = false}
                    29-> { soldierBlack2.coordinateX = i
                        soldierBlack2.coordinateY = j
                        soldierBlack2.isShowed = false
                        soldierBlack2.isDefeat = false}
                    30-> { soldierBlack3.coordinateX = i
                        soldierBlack3.coordinateY = j
                        soldierBlack3.isShowed = false
                        soldierBlack3.isDefeat = false}
                    31-> { soldierBlack4.coordinateX = i
                        soldierBlack4.coordinateY = j
                        soldierBlack4.isShowed = false
                        soldierBlack4.isDefeat = false}
                    32-> { soldierBlack5.coordinateX = i
                        soldierBlack5.coordinateY = j
                        soldierBlack5.isShowed = false
                        soldierBlack5.isDefeat = false}

                }

                count++
            }
        }

        for (i in 0 until rows) {
            for (j in 0 until columns) {
                print(boardArray[i][j])
                print(", ")
            }
            println()
        }
    }

    fun refreshCoordinate() {
        var count = 0
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                this.boardArray[i][j] = arrayList[count]

                when(arrayList[count]) {
                    1 -> { generalRed.coordinateX = i
                        generalRed.coordinateY = j }
                    2 -> { guardRed1.coordinateX = i
                        guardRed1.coordinateY = j}
                    3 -> { guardRed2.coordinateX = i
                        guardRed2.coordinateY = j}
                    4 -> { handRed1.coordinateX = i
                        handRed1.coordinateY = j}
                    5 -> { handRed2.coordinateX = i
                        handRed2.coordinateY = j}
                    6 -> { castleRed1.coordinateX = i
                        castleRed1.coordinateY = j}
                    7 -> { castleRed2.coordinateX = i
                        castleRed2.coordinateY = j}
                    8 -> { knightRed1.coordinateX = i
                        knightRed1.coordinateY = j}
                    9 -> { knightRed2.coordinateX = i
                        knightRed2.coordinateY = j}
                    10-> { cannonRed1.coordinateX = i
                        cannonRed1.coordinateY = j}
                    11-> { cannonRed2.coordinateX = i
                        cannonRed2.coordinateY = j}
                    12-> { soldierRed1.coordinateX = i
                        soldierRed1.coordinateY = j}
                    13-> { soldierRed2.coordinateX = i
                        soldierRed2.coordinateY = j}
                    14-> { soldierRed3.coordinateX = i
                        soldierRed3.coordinateY = j}
                    15-> { soldierRed4.coordinateX = i
                        soldierRed4.coordinateY = j}
                    16-> { soldierRed5.coordinateX = i
                        soldierRed5.coordinateY = j}
                    17-> { generalBlack.coordinateX = i
                        generalBlack.coordinateY = j}
                    18-> { guardBlack1.coordinateX = i
                        guardBlack1.coordinateY = j}
                    19-> { guardBlack2.coordinateX = i
                        guardBlack2.coordinateY = j}
                    20-> { handBlack1.coordinateX = i
                        handBlack1.coordinateY = j}
                    21-> { handBlack2.coordinateX = i
                        handBlack2.coordinateY = j}
                    22-> { castleBlack1.coordinateX = i
                        castleBlack1.coordinateY = j}
                    23-> { castleBlack2.coordinateX = i
                        castleBlack2.coordinateY = j}
                    24-> { knightBlack1.coordinateX = i
                        knightBlack1.coordinateY = j}
                    25-> { knightBlack2.coordinateX = i
                        knightBlack2.coordinateY = j}
                    26-> { cannonBlack1.coordinateX = i
                        cannonBlack1.coordinateY = j}
                    27-> { cannonBlack2.coordinateX = i
                        cannonBlack2.coordinateY = j}
                    28-> { soldierBlack1.coordinateX = i
                        soldierBlack1.coordinateY = j}
                    29-> { soldierBlack2.coordinateX = i
                        soldierBlack2.coordinateY = j}
                    30-> { soldierBlack3.coordinateX = i
                        soldierBlack3.coordinateY = j}
                    31-> { soldierBlack4.coordinateX = i
                        soldierBlack4.coordinateY = j}
                    32-> { soldierBlack5.coordinateX = i
                        soldierBlack5.coordinateY = j}

                }

                count++
            }
        }

        for (i in 0 until rows) {
            for (j in 0 until columns) {
                print(boardArray[i][j])
                print(", ")
            }
            println()
        }
    }

    fun getChessFromId(id: Int): Chess {
        val chess: Chess = Zero()
        return when(id) {
            1  -> generalRed
            2  -> guardRed1
            3  -> guardRed2
            4  -> handRed1
            5  -> handRed2
            6  -> castleRed1
            7  -> castleRed2
            8  -> knightRed1
            9  -> knightRed2
            10 -> cannonRed1
            11 -> cannonRed2
            12 -> soldierRed1
            13 -> soldierRed2
            14 -> soldierRed3
            15 -> soldierRed4
            16 -> soldierRed5
            17 -> generalBlack
            18 -> guardBlack1
            19 -> guardBlack2
            20 -> handBlack1
            21 -> handBlack2
            22 -> castleBlack1
            23 -> castleBlack2
            24 -> knightBlack1
            25 -> knightBlack2
            26 -> cannonBlack1
            27 -> cannonBlack2
            28 -> soldierBlack1
            29 -> soldierBlack2
            30 -> soldierBlack3
            31 -> soldierBlack4
            32 -> soldierBlack5
            else -> chess
        }
    }

    fun isAnyChessOnCoordinate(row: Int, column: Int): Boolean {
        var ret = false

        if (boardArray[row][column] != 0) {
            ret = true
        }

        return ret
    }

    fun chessCouldBeReplace(srcChess: Chess, destChess: Chess): Boolean {
        var ret = false

        if (srcChess.level == 7) {//general
            Log.e(mTAG, "General can't take down soldier")
            ret = (destChess.level > 1)
        } else if (srcChess.level == 2) { //cannon, special
            if (srcChess.coordinateX != destChess.coordinateX && srcChess.coordinateY != destChess.coordinateY) { //not the same row or column
                Log.e(mTAG, "Cannon could only take down along a line")
                ret = false
            } else if (srcChess.isNeighbor(destChess.coordinateX, destChess.coordinateY)) {
                Log.e(mTAG, "Cannon can't take down it's neighbor")
                ret = false
            } else {
                if (srcChess.isCannonTarget(destChess.coordinateX, destChess.coordinateY, this)) {
                    ret = true
                } else {
                    Log.e(mTAG, "dest's coordinate can't be take down by a cannon")
                }
            }
        } else if (srcChess.level == 1) { //soldier, can take down general and soldier
            if (destChess.level == 7 || destChess.level == 1) {
                if (srcChess.isNeighbor(destChess.coordinateX, destChess.coordinateY)) { // only neighbor can be ate

                    ret = true
                } else {
                    Log.e(mTAG, "only neighbor can be ate")
                }
            } else {
                Log.e(mTAG, "soldier can only take down general and soldier")
            }
        } else { //follow the rule: low level can't eat high level, and only neighbor can be ate
            if (srcChess.level >= destChess.level) {
                ret = true
            } else {
                Log.e(mTAG, "dest level is higher than src")
            }
        }

        return ret
    }

    fun getCoordinateX(index: Int): Int {
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                if (boardArray[i][j] == index) {
                    return i
                }
            }
        }

        return -1
    }
    fun getCoordinateY(index: Int): Int {
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                if (boardArray[i][j] == index) {
                    return j
                }
            }
        }

        return -1
    }


    fun isNeighbor(srcIndex: Int, destIndex: Int): Boolean {
        Log.e(mTAG, "=== isNeighbor start ===")
        Log.d(mTAG, "srcIndex = $srcIndex, destIndex = $destIndex")
        var ret = false
        var srcX : Int = 0
        var srcY : Int = 0
        var destX : Int = 0
        var destY : Int = 0

        var count = 0
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                if (count == srcIndex) {
                    srcX = i
                    srcY = j
                    Log.d(mTAG, "found srcIndex in x = $srcX ,y = $srcY")
                }
                count++
            }
        }

        count = 0
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                if (count == destIndex) {
                    destX = i
                    destY = j
                    Log.d(mTAG, "found destIndex in x = $destX ,y = $destY")
                }
                count++
            }
        }

        val diffX = destX - srcX
        val diffY = destY - srcY

        val totalDiff = diffX + diffY

        if (totalDiff == 1 || totalDiff == -1) {

            ret = true
        }

        Log.e(mTAG, "=== isNeighbor end ===")

        return ret
    }
}