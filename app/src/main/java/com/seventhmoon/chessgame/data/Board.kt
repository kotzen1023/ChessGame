package com.seventhmoon.chessgame.data

import android.content.Context
import android.graphics.Color
import android.util.Log
import com.seventhmoon.chessgame.R
import java.util.*
import kotlin.collections.ArrayList


class Board(rows: Int, columns: Int, mContext: Context) {
    private val mTAG = Board::class.java.name
    private var move: Int = 0
    private var rows: Int = 0
    private var columns: Int = 0
    private val mContext = mContext
    var boardArray: Array<IntArray>  //Array(rows) {Array(columns) {0}}
    val arrayList: ArrayList<Int> = ArrayList()

    val boardStateStack: Stack<BoardState> = Stack()

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

    val robot1ShowedList: ArrayList<Int> = ArrayList()
    val robot2ShowedList: ArrayList<Int> = ArrayList()

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

        //init as black
        generalBlack.kind = true
        guardBlack1.kind = true
        guardBlack2.kind = true
        handBlack1.kind = true
        handBlack2.kind = true
        castleBlack1.kind = true
        castleBlack2.kind = true
        knightBlack1.kind = true
        knightBlack2.kind = true
        cannonBlack1.kind = true
        cannonBlack2.kind = true
        soldierBlack1.kind = true
        soldierBlack2.kind = true
        soldierBlack3.kind = true
        soldierBlack4.kind = true
        soldierBlack5.kind = true


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
                if (j%4 != 3)
                    print(", ")
            }
            println()
        }

        //init boardState
        val boardState = BoardState(0, boardArray)
        boardStateStack.push(boardState)
        Log.e(mTAG, "boardStateStack size = ${boardStateStack.size}")

        showCurrentBoardState()
    }

    fun getMove():  Int {
        return move
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

        move = 0

        arrayList.clear()
        boardStateStack.clear()

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
                if (j%4 != 3)
                    print(", ")
            }
            println()
        }

        //init boardState
        val boardState = BoardState(0, boardArray)
        boardStateStack.push(boardState)
        Log.e(mTAG, "boardStateStack size = ${boardStateStack.size}")

        showCurrentBoardState()
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
                if (j%4 != 3)
                    print(", ")
            }
            println()
        }
    }

    fun getChessFromId(id: Int): Chess {

        //find coordinate
        var x = 0
        var y = 0
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                if (boardArray[i][j] == id) {
                    x = i
                    y = j
                }
            }
        }

        val chess: Chess = Zero()
        chess.coordinateX = x
        chess.coordinateY = y
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
                if (srcChess.isCannonTarget(destChess.coordinateX, destChess.coordinateY, destChess.id, this)) {
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

    fun chooseShowFromHidden (): Int {
        var ret = -1
        //collect
        val notChooseYetArray: ArrayList<Int> = ArrayList()
        for(i in 0 until 32) {
            var add = false
            when(arrayList[i]) {
                1 -> add = !generalRed.isShowed
                2 -> add = !guardRed1.isShowed
                3 -> add = !guardRed2.isShowed
                4 -> add = !handRed1.isShowed
                5 -> add = !handRed2.isShowed
                6 -> add = !castleRed1.isShowed
                7 -> add = !castleRed2.isShowed
                8 -> add = !knightRed1.isShowed
                9 -> add = !knightRed2.isShowed
                10-> add = !cannonRed1.isShowed
                11-> add = !cannonRed2.isShowed
                12-> add = !soldierRed1.isShowed
                13-> add = !soldierRed2.isShowed
                14-> add = !soldierRed3.isShowed
                15-> add = !soldierRed4.isShowed
                16-> add = !soldierRed5.isShowed
                17-> add = !generalBlack.isShowed
                18-> add = !guardBlack1.isShowed
                19-> add = !guardBlack2.isShowed
                20-> add = !handBlack1.isShowed
                21-> add = !handBlack2.isShowed
                22-> add = !castleBlack1.isShowed
                23-> add = !castleBlack2.isShowed
                24-> add = !knightBlack1.isShowed
                25-> add = !knightBlack2.isShowed
                26-> add = !cannonBlack1.isShowed
                27-> add = !cannonBlack2.isShowed
                28-> add = !soldierBlack1.isShowed
                29-> add = !soldierBlack2.isShowed
                30-> add = !soldierBlack3.isShowed
                31-> add = !soldierBlack4.isShowed
                32-> add = !soldierBlack5.isShowed
            }

            if (add)
                notChooseYetArray.add(arrayList[i])
        }

        Log.e(mTAG, "notChooseYetArray.size = ${notChooseYetArray.size}")

        //random choose from
        var id = 0
        if (notChooseYetArray.size > 0) {
            notChooseYetArray.shuffle()
            //then select first
            id = notChooseYetArray[0]
        } else {
            return ret
        }

        //find id in original array
        for (i in 0 until arrayList.size) {
            if (arrayList[i] == id) {
                ret = i
                break
            }
        }

        Log.e(mTAG, "found $id in arrayList[$ret]")

        return ret
    }

    fun getNameFromId(index: Int): String {
        return when(index) {
            1 -> mContext.getString(R.string.general_red)
            2 -> mContext.getString(R.string.guard_red)
            3 -> mContext.getString(R.string.guard_red)
            4 -> mContext.getString(R.string.hand_red)
            5 -> mContext.getString(R.string.hand_red)
            6 -> mContext.getString(R.string.castle_red)
            7 -> mContext.getString(R.string.castle_red)
            8 -> mContext.getString(R.string.knight_red)
            9 -> mContext.getString(R.string.knight_red)
            10 -> mContext.getString(R.string.cannon_red)
            11 -> mContext.getString(R.string.cannon_red)
            12 -> mContext.getString(R.string.soldier_red)
            13 -> mContext.getString(R.string.soldier_red)
            14 -> mContext.getString(R.string.soldier_red)
            15 -> mContext.getString(R.string.soldier_red)
            16 -> mContext.getString(R.string.soldier_red)
            17 -> mContext.getString(R.string.general_blue)
            18 -> mContext.getString(R.string.guard_blue)
            19 -> mContext.getString(R.string.guard_blue)
            20 -> mContext.getString(R.string.hand_blue)
            21 -> mContext.getString(R.string.hand_blue)
            22 -> mContext.getString(R.string.castle_blue)
            23 -> mContext.getString(R.string.castle_blue)
            24 -> mContext.getString(R.string.knight_blue)
            25 -> mContext.getString(R.string.knight_blue)
            26 -> mContext.getString(R.string.cannon_blue)
            27 -> mContext.getString(R.string.cannon_blue)
            28 -> mContext.getString(R.string.soldier_blue)
            29 -> mContext.getString(R.string.soldier_blue)
            30 -> mContext.getString(R.string.soldier_blue)
            31 -> mContext.getString(R.string.soldier_blue)
            32 -> mContext.getString(R.string.soldier_blue)
            else -> ""
        }
    }

    fun getColorFromId(index: Int): Int {

        var color = 0
        if (index in 1..16) {
            color = Color.RED
        } else if (index in 17..32) {
            color = Color.BLUE
        } else {
            color = Color.BLACK
        }

        return color
    }

    fun saveCurrentState() {
        Log.e(mTAG, "=== board saveCurrentState start ==")

        if (boardStateStack.empty()) {
            val boardState = BoardState(0, boardArray)
            boardStateStack.push(boardState)

            showCurrentBoardState()
        } else {
            move++
            val newBoardState = BoardState(move, boardArray)
            boardStateStack.push(newBoardState)

            showCurrentBoardState()
        }
        Log.e(mTAG, "=== board saveCurrentState end ==")
    }


    private fun showCurrentBoardState() {
        Log.d(mTAG, "=== show current stack start")
        val currentBoardState = boardStateStack.peek()
        for (i in 0 until currentBoardState.array.size) {
            for (j in 0 until currentBoardState.array[i].size) {
                print(currentBoardState.array[i][j])
                if (j%4 != 3)
                    print(", ")
            }
            println()
        }
        Log.d(mTAG, "=== show current stack end")
    }

    fun findEnemyIsShowed(id: Int): ArrayList<Int> {
        val chessIsShowedArray: ArrayList<Int> = ArrayList()

        when (id) {
            in 1..16 -> {
                for (i in 0 until arrayList.size) {
                    if (arrayList[i] in 17..32) {
                        if (getShowStatusById(arrayList[i])) {
                            chessIsShowedArray.add(arrayList[i])
                        }
                    }
                }
            }
            in 17..32 -> {
                for (i in 0 until arrayList.size) {
                    if (arrayList[i] in 1..16) {
                        if (getShowStatusById(arrayList[i]))
                            chessIsShowedArray.add(arrayList[i])
                    }
                }
            }
            else -> Log.e(mTAG,"unknown type")
        }




        return chessIsShowedArray
    }

    private fun isIdthesameKind(srcId: Int, destId: Int): Boolean {
        var ret = false
        when(srcId) {
            in 1..16 -> {
                if (destId in 1..16) {
                    ret = true
                }
            }
            in 17..32 -> {
                if (destId in 17..32) {
                    ret = true
                }
            }
        }
        return ret
    }

    private fun getShowStatusById(id: Int):Boolean {
        return when(id) {
            1 -> generalRed.isShowed
            2 -> guardRed1.isShowed
            3 -> guardRed2.isShowed
            4 -> handRed1.isShowed
            5 -> handRed2.isShowed
            6 -> castleRed1.isShowed
            7 -> castleRed2.isShowed
            8 -> knightRed1.isShowed
            9 -> knightRed2.isShowed
            10-> cannonRed1.isShowed
            11-> cannonRed2.isShowed
            12-> soldierRed1.isShowed
            13-> soldierRed2.isShowed
            14-> soldierRed3.isShowed
            15-> soldierRed4.isShowed
            16-> soldierRed5.isShowed
            17-> generalBlack.isShowed
            18-> guardBlack1.isShowed
            19-> guardBlack2.isShowed
            20-> handBlack1.isShowed
            21-> handBlack2.isShowed
            22-> castleBlack1.isShowed
            23-> castleBlack2.isShowed
            24-> knightBlack1.isShowed
            25-> knightBlack2.isShowed
            26-> cannonBlack1.isShowed
            27-> cannonBlack2.isShowed
            28-> soldierBlack1.isShowed
            29-> soldierBlack2.isShowed
            30-> soldierBlack3.isShowed
            31-> soldierBlack4.isShowed
            32-> soldierBlack5.isShowed
            else -> false
        }
    }

    fun addIdToRobot1ShowedListOnBoard(chess_id: Int) {
        Log.e(mTAG, "=== addIdTo Robot1 ShowedListOnBoard add ${getNameFromId(chess_id)}{${getCoordinateX(chess_id)}, ${getCoordinateY(chess_id)}} ===")
        Log.d(mTAG, "Before: $robot1ShowedList")
        robot1ShowedList.add(chess_id)
        robot1ShowedList.sort()
        Log.d(mTAG, "After : $robot1ShowedList")
        Log.e(mTAG, "=== addIdTo Robot1 ShowedListOnBoard end ===")
    }

    fun removeIdFromRobot1ShowedListOnBoard(chess_id: Int) {
        Log.e(mTAG, "=== removeIdFrom Robot1 ShowedListOnBoard remove ${getNameFromId(chess_id)}{${getCoordinateX(chess_id)}, ${getCoordinateY(chess_id)}} ===")
        Log.d(mTAG, "Before: $robot1ShowedList")
        var index: Int = -1
        for(i in 0 until robot1ShowedList.size) {
            if (chess_id == robot1ShowedList[i]) {
                index = i
            }
        }

        if (index > -1)
            robot1ShowedList.removeAt(index)
        Log.d(mTAG, "After : $robot1ShowedList")
        Log.e(mTAG, "=== removeIdFrom Robot1 ShowedListOnBoard end ===")
    }

    fun addIdToRobot2ShowedListOnBoard(chess_id: Int) {
        Log.e(mTAG, "=== addIdTo Robot2 ShowedListOnBoard add ${getNameFromId(chess_id)}{${getCoordinateX(chess_id)}, ${getCoordinateY(chess_id)}} ===")
        Log.d(mTAG, "Before: $robot2ShowedList")
        robot2ShowedList.add(chess_id)
        robot2ShowedList.sort()
        Log.d(mTAG, "After : $robot2ShowedList")
        Log.e(mTAG, "=== addIdTo Robot2 ShowedListOnBoard end ===")
    }

    fun removeIdFromRobot2ShowedListOnBoard(chess_id: Int) {
        Log.e(mTAG, "=== removeIdFrom Robot2 ShowedListOnBoard remove ${getNameFromId(chess_id)}{${getCoordinateX(chess_id)}, ${getCoordinateY(chess_id)}} ===")
        Log.d(mTAG, "Before: $robot2ShowedList")
        var index: Int = -1
        for(i in 0 until robot2ShowedList.size) {
            if (chess_id == robot2ShowedList[i]) {
                index = i
            }
        }

        if (index > -1)
            robot2ShowedList.removeAt(index)
        Log.d(mTAG, "After : $robot2ShowedList")
        Log.e(mTAG, "=== removeIdFrom Robot2 ShowedListOnBoard end ===")
    }

    fun findTargetNeighbor(srcId: Int, target_id: Int, target_X: Int, target_Y: Int): ArrayList<Int> {
        Log.e(mTAG, "=== findTargetNeighbor start srcId = $srcId, target_X = $target_X, target_Y = $target_Y  ===")
        val chessIsShowedArray: ArrayList<Int> = ArrayList()

        val xDown = target_X + 1
        val xUp   = target_X - 1
        val yDown = target_Y + 1
        val yUp   = target_Y - 1

        if (xDown in 0..7) {
            if (boardArray[xDown][target_Y] > 0 && getShowStatusById(boardArray[xDown][target_Y])) { //not space and is showed
                if (!isIdthesameKind(srcId, boardArray[xDown][target_Y])) {
                    chessIsShowedArray.add(boardArray[xDown][target_Y])
                }
            }
        }

        if (xUp in 0..7) {
            if (boardArray[xUp][target_Y] > 0 && getShowStatusById(boardArray[xUp][target_Y])) {
                if (!isIdthesameKind(srcId, boardArray[xUp][target_Y])) {
                    chessIsShowedArray.add(boardArray[xUp][target_Y])
                }
            }
        }

        if (yDown in 0..3) {
            if (boardArray[target_X][yDown] > 0 && getShowStatusById(boardArray[target_X][yDown])) {
                if (!isIdthesameKind(srcId, boardArray[target_X][yDown])) {
                    chessIsShowedArray.add(boardArray[target_X][yDown])
                }
            }
        }

        if (yUp in 0..3) {
            if (boardArray[target_X][yUp] > 0 && getShowStatusById(boardArray[target_X][yUp])) {
                if (!isIdthesameKind(srcId, boardArray[target_X][yUp])) {
                    chessIsShowedArray.add(boardArray[target_X][yUp])
                }
            }
        }

        if (chessIsShowedArray.size > 0) {
            Log.d(mTAG, "${getNameFromId(srcId)}{${getCoordinateX(srcId)}, ${getCoordinateY(srcId)}}'s possible neighbor enemy list if beat ${getNameFromId(target_id)}{$target_X , $target_Y}: ")
            for (i in 0 until chessIsShowedArray.size) {
                print(getNameFromId(chessIsShowedArray[i]))
                print("{")
                print(getCoordinateX(chessIsShowedArray[i]))
                print(", ")
                print(getCoordinateY(chessIsShowedArray[i]))
                print("}")
                println()
            }
            Log.d(mTAG, "==============================")
        }


        Log.e(mTAG, "=== findTargetNeighbor end ===")

        return chessIsShowedArray
    }

    fun findTargetIsCannonTarget(srcId: Int, target_id: Int, target_X: Int, target_Y: Int): ArrayList<Int> {
        Log.e(mTAG, "=== findTargetIsCannonTarget start srcId = $srcId, target_X = $target_X, target_Y = $target_Y  ===")
        val chessIsShowedArray: ArrayList<Int> = ArrayList()

        var found = false
        for (i in 0 until robot1ShowedList.size) {
            if (srcId == robot1ShowedList[i]) {
                found = true
                break
            }
        }

        Log.e(mTAG, "found = $found")

        if (found) { //use robot2ShowedList
            for (i in 0 until robot2ShowedList.size) {
                when(robot2ShowedList[i]) {
                    10,11,26,27 -> {
                        val enemyX = getCoordinateX(robot2ShowedList[i])
                        val enemyY = getCoordinateY(robot2ShowedList[i])

                        Log.d(mTAG, "enemy (cannon) {$enemyX, $enemyY}")

                        if (target_X == enemyX || target_Y == enemyY) { //is on the same line
                            var foundCount = 0
                            if (target_X == enemyX) {

                                if (target_Y > enemyY) {
                                    for(j in enemyY+1 until target_Y) {

                                        if (isAnyChessOnCoordinate(target_X, j))
                                            foundCount++
                                    }
                                } else {
                                    for(j in target_Y+1 until enemyY) {

                                        if (isAnyChessOnCoordinate(target_X, j))
                                            foundCount++
                                    }
                                }

                                if (foundCount == 1) {
                                    Log.d(mTAG, "${getNameFromId(robot2ShowedList[i])}{${getCoordinateX(robot2ShowedList[i])}, ${getCoordinateY(robot2ShowedList[i])}} is a cannon aim at target {$target_X, $target_Y}")
                                    chessIsShowedArray.add(robot2ShowedList[i])
                                }

                            } else { //targetY == enemyY
                                if (target_X > enemyX) {
                                    for(j in enemyX+1 until target_X) {

                                        if (isAnyChessOnCoordinate(j, target_Y))
                                            foundCount++
                                    }
                                } else {
                                    for(j in target_X+1 until enemyX) {

                                        if (isAnyChessOnCoordinate(j, target_Y))
                                            foundCount++
                                    }
                                }

                                if (foundCount == 1) {
                                    Log.d(mTAG, "${getNameFromId(robot2ShowedList[i])}{${getCoordinateX(robot2ShowedList[i])}, ${getCoordinateY(robot2ShowedList[i])}} is a cannon aim at target {$target_X, $target_Y}")
                                    chessIsShowedArray.add(robot2ShowedList[i])
                                }
                            }
                        } else {
                            Log.e(mTAG, "possible enemy ${getNameFromId(robot2ShowedList[i])}{${getCoordinateX(robot2ShowedList[i])}, ${getCoordinateY(robot2ShowedList[i])}} is not aim the target{$target_X, $target_Y}")
                        }
                    }
                }
            }
        } else { //use robot1ShowedList
            for (i in 0 until robot1ShowedList.size) {
                when(robot1ShowedList[i]) {
                    10,11,26,27 -> {
                        val enemyX = getCoordinateX(robot1ShowedList[i])
                        val enemyY = getCoordinateY(robot1ShowedList[i])

                        Log.d(mTAG, "enemy (cannon) {$enemyX, $enemyY}")

                        if (target_X == enemyX || target_Y == enemyY) { //is on the same line
                            var foundCount = 0
                            if (target_X == enemyX) {

                                if (target_Y > enemyY) {
                                    for(j in enemyY+1 until target_Y) {

                                        if (isAnyChessOnCoordinate(target_X, j))
                                            foundCount++
                                    }
                                } else {
                                    for(j in target_Y+1 until enemyY) {

                                        if (isAnyChessOnCoordinate(target_X, j))
                                            foundCount++
                                    }
                                }

                                if (foundCount == 1) {
                                    Log.d(mTAG, "${getNameFromId(robot1ShowedList[i])}{${getCoordinateX(robot1ShowedList[i])}, ${getCoordinateY(robot1ShowedList[i])}} is a cannon aim at target {$target_X, $target_Y}")
                                    chessIsShowedArray.add(robot1ShowedList[i])
                                }

                            } else { //targetY == enemyY
                                if (target_X > enemyX) {
                                    for(j in enemyX+1 until target_X) {

                                        if (isAnyChessOnCoordinate(j, target_Y))
                                            foundCount++
                                    }
                                } else {
                                    for(j in target_X+1 until enemyX) {

                                        if (isAnyChessOnCoordinate(j, target_Y))
                                            foundCount++
                                    }
                                }

                                if (foundCount == 1) {
                                    Log.d(mTAG, "${getNameFromId(robot1ShowedList[i])}{${getCoordinateX(robot1ShowedList[i])}, ${getCoordinateY(robot1ShowedList[i])}} is a cannon aim at target {$target_X, $target_Y}")
                                    chessIsShowedArray.add(robot1ShowedList[i])
                                }
                            }
                        } else {
                            Log.e(mTAG, "possible enemy ${getNameFromId(robot1ShowedList[i])}{${getCoordinateX(robot1ShowedList[i])}, ${getCoordinateY(robot1ShowedList[i])}} is not aim the target{$target_X, $target_Y}")
                        }
                    }
                }
            }
        }

        if (chessIsShowedArray.size > 0) {
            Log.d(mTAG, "${getNameFromId(srcId)}{${getCoordinateX(srcId)}, ${getCoordinateY(srcId)}}'s possible cannon enemy list if beat ${getNameFromId(target_id)}{$target_X , $target_Y}: ")
            for (i in 0 until chessIsShowedArray.size) {
                print(getNameFromId(chessIsShowedArray[i]))
                print("{")
                print(getCoordinateX(chessIsShowedArray[i]))
                print(", ")
                print(getCoordinateY(chessIsShowedArray[i]))
                print("}")
                println()
            }
            Log.d(mTAG, "==============================")
        }

        Log.e(mTAG, "=== findTargetIsCannonTarget end")

        return chessIsShowedArray
    }
}