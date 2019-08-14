package com.seventhmoon.chessgame.data

import android.util.Log


class Board(rows: Int, columns: Int) {
    private val mTAG = Board::class.java.name

    private var rows: Int = 0
    private var columns: Int = 0
    var boardArray: Array<IntArray>  //Array(rows) {Array(columns) {0}}
    val arrayList: ArrayList<Int> = ArrayList()

    private var generalRed: General = General()
    private var guardRed1: Guard = Guard()
    private var guardRed2: Guard = Guard()
    private var handRed1: Hand = Hand()
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

    fun isAnyChessOnCoordinate(row: Int, column: Int): Boolean {
        var ret = false

        if (boardArray[row][column] != 0) {
            ret = true
        }

        return ret
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
}