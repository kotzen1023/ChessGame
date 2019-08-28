package com.seventhmoon.chessgame.robot

import android.content.Context
import android.content.Intent
import android.util.Log
import com.seventhmoon.chessgame.data.Board
import com.seventhmoon.chessgame.data.Chess
import com.seventhmoon.chessgame.data.Constants
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class SimpleRobot(name: String, context: Context) {
    private val mTAG = SimpleRobot::class.java.name
    var robotName: String = ""
    var chooseKind: Boolean = false //default is red

    private val aliveAndIsShowedList: ArrayList<Int> = ArrayList()
    private val enemyAliveShowedList: ArrayList<Int> = ArrayList()
    private val opponentCouldBeBeatList: ArrayList<Int> = ArrayList()

    //for every move calculate
    private val possibleCouldBeatList: ArrayList<Int> = ArrayList()
    private val possibleCouldTakeDownList: HashMap<Int, ArrayList<Int>> = HashMap()
    private val possibleCouldLostAfterBeat: HashMap<Int, ArrayList< HashMap<Int, ArrayList<Int>> > > = HashMap()
    private val possibleCouldLostList: ArrayList<Int> = ArrayList()

    var state: CurrentState = CurrentState.STATE_NONE
    private var mContext: Context ?= null

    enum class CurrentState {
        STATE_NONE, STATE_INIT, STATE_RUNNING
    }



    init {
        Log.d(mTAG, "=== SimpleRobot: ($robotName) init start ===")
        robotName = name

        aliveAndIsShowedList.clear()
        enemyAliveShowedList.clear()
        opponentCouldBeBeatList.clear()
        mContext = context

        state = CurrentState.STATE_INIT
        Log.d(mTAG, "=== SimpleRobot: ($robotName) init end ===")
    }



    fun reset() {
        Log.d(mTAG, "=== SimpleRobot: ($robotName) reset start ===")
        //robotName = name
        //chooseKind = kind
        //clear stack
        chooseKind = false
        aliveAndIsShowedList.clear()
        enemyAliveShowedList.clear()
        opponentCouldBeBeatList.clear()


        state = CurrentState.STATE_INIT

        Log.d(mTAG, "=== SimpleRobot: ($robotName) end ===")

    }

    fun showChess(id: Int, board: Board) {
        Log.e(mTAG, "=== $robotName showChess start: id = $id, move = ${board.getMove()}")

        if (board.getMove() == 0) { //first move
            if (id > 16) {
                chooseKind = true
            } else {
                val setIntent = Intent()
                setIntent.action = Constants.ACTION.ACTION_SET_ROBOT2_KIND_AS_TRUE_ACTION
                mContext!!.sendBroadcast(setIntent)
            }
            Log.d(mTAG, "add $id to aliveAndIsShowedList")
            addChessToAliveList(id, board)
        } else { // not first move
            val kind = (id > 16)
            if (chooseKind == kind) {
                addChessToAliveList(id, board)
            } else {
                val saveIntent = Intent()
                saveIntent.action = Constants.ACTION.ACTION_SAVE_ID_TO_ANOTHER_ROBOT_ALIVE_LIST_ACTION
                saveIntent.putExtra("ID", id)
                mContext!!.sendBroadcast(saveIntent)
            }
        }

        Log.e(mTAG, "=== $robotName showChess end === ")
    }

    fun randomChoose(board: Board): Int {
        //var ret: Int = 0
        Log.e(mTAG, "=== $robotName (kind = $chooseKind) randomChoose start ===")

        val selectIndex = board.chooseShowFromHidden()

        if (selectIndex > -1) {
            val chess = board.getChessFromId(board.getArrayItem(selectIndex))
            Log.d(mTAG, "chess.id = ${chess.id}, kind = ${chess.kind}")
            if (!chess.isShowed) {
                chess.isShowed = true

                //see if this chess's kind is the same as us
                if (chess.kind == chooseKind) {
                    //add this chess to aliveAndIsShowedList
                    Log.d(mTAG, "Add ${chess.id} to $robotName's aliveAndIsShowedList, it's ${board.getNameFromId(chess.id)}")
                    addChessToAliveList(chess.id, board)
                } else { //send broadcast to save to another robot aliveAndIsShowedList
                    val saveIntent = Intent()
                    saveIntent.action = Constants.ACTION.ACTION_SAVE_ID_TO_ANOTHER_ROBOT_ALIVE_LIST_ACTION
                    saveIntent.putExtra("ID", chess.id)
                    mContext!!.sendBroadcast(saveIntent)
                }
            }
            Log.d(mTAG, "Random select chess id is ${chess.id}, it's ${board.getNameFromId(chess.id)}")
        }

        Log.e(mTAG, "=== $robotName randomChoose end ===")

        return selectIndex
    }

    fun activatedChessMoveOrEat(board: Board) {

        Log.e(mTAG, "=== $robotName activatedChessMoveOrEat start ===")

        /*print("aliveAndIsShowedList=[")
        for (i in 0 until aliveAndIsShowedList.size) {
            print(aliveAndIsShowedList[i])
            if (i < aliveAndIsShowedList.size - 1)
                print(", ")
        }
        print("]")*/


        Log.e(mTAG, "=== $robotName activatedChessMoveOrEat end ===")
    }

    fun thinking(): Int {
        Log.e(mTAG, "=== $robotName thinking start ===")
        //find a chess you can take down opponent
        var ret = 0 //surrender
        if (aliveAndIsShowedList.size == 0) { // 1
            Log.d(mTAG, "aliveAndIsShowedList.size = 0 => random choose")
            ret = 1 // choose showing
        } else if (aliveAndIsShowedList.size > 0) { // 2
            ret = 2 //
        }

        Log.e(mTAG, "=== $robotName thinking end ===")

        return ret
    }

    fun showAliveList() {
        Log.e(mTAG, "=== $robotName showAliveList start ===")

        Log.d(mTAG, "aliveAndIsShowedList = $aliveAndIsShowedList")
        Log.e(mTAG, "=== $robotName showAliveList end ===")
    }

    fun showEnemyList() {
        Log.e(mTAG, "=== $robotName showEnemyList start ===")

        Log.d(mTAG, "enemyAliveShowedList = $enemyAliveShowedList")
        Log.e(mTAG, "=== $robotName showEnemyList end ===")
    }

    fun addChessToAliveList(id: Int, board: Board): Int {
        Log.d(mTAG, "=== $robotName add $id to addChessToAliveList start ===")
        aliveAndIsShowedList.add(id)
        aliveAndIsShowedList.sort()

        //add to board
        if (robotName == "Robot1") {
            board.addIdToRobot1ShowedListOnBoard(id)
        } else {
            board.addIdToRobot2ShowedListOnBoard(id)
        }

        Log.d(mTAG, "aliveAndIsShowedList.size = ${aliveAndIsShowedList.size}")
        Log.d(mTAG, "=== $robotName add $id to addChessToAliveList end ===")

        //inform enemy to add to list
        val infoIntent = Intent()
        infoIntent.action = Constants.ACTION.ACTION_SEND_ID_TO_ANOTHER_ROBOT_IN_ENEMY_LIST_ACTION
        infoIntent.putExtra("ENEMY_ID", id)
        infoIntent.putExtra("ENEMY_KIND", chooseKind)
        mContext!!.sendBroadcast(infoIntent)

        return aliveAndIsShowedList.size
    }

    fun removeChessToAliveList(id: Int, board: Board): Int {
        Log.d(mTAG, "=== $robotName remove $id removeChessToAliveList start ===")
        var index: Int = -1
        for(i in 0 until aliveAndIsShowedList.size) {
            if (id == aliveAndIsShowedList[i]) {
                index = i
            }
        }

        if (index > 0) {
            aliveAndIsShowedList.removeAt(index)
        }
        Log.d(mTAG, "aliveAndIsShowedList.size = ${aliveAndIsShowedList.size}")

        //remove from board
        if (robotName == "Robot1") {
            board.removeIdFromRobot1ShowedListOnBoard(id)
        } else {
            board.removeIdFromRobot2ShowedListOnBoard(id)
        }

        Log.d(mTAG, "=== $robotName removeChessToAliveList end ===")
        return aliveAndIsShowedList.size
    }

    fun addChessToEnemyList(enemy_id: Int): Int {
        Log.d(mTAG, "=== $robotName add $enemy_id to addChessToEnemyList start ===")
        enemyAliveShowedList.add(enemy_id)
        enemyAliveShowedList.sort()
        Log.d(mTAG, "=== $robotName addChessToEnemyList end ===")
        return enemyAliveShowedList.size
    }

    fun removeChessFromEnemyList(enemy_id: Int): Int {
        Log.d(mTAG, "=== $robotName remove $enemy_id removeChessFromEnemyList start ===")

        var index: Int = -1
        for(i in 0 until enemyAliveShowedList.size) {
            if (enemy_id == enemyAliveShowedList[i]) {
                index = i
            }
        }

        if (index > 0) {
            enemyAliveShowedList.removeAt(index)
        }

        Log.d(mTAG, "=== $robotName removeChessFromEnemyList end ===")
        return enemyAliveShowedList.size
    }

    fun addChessToPossibleBeatList(id: Int) {

        var found = false
        for (i in 0 until possibleCouldBeatList.size) {
            if (id == possibleCouldBeatList[i]) {
                found = true
                break
            }
        }

        if (!found) {
            possibleCouldBeatList.add(id)
            possibleCouldBeatList.sort()
        }
    }

    fun showPossibleBeatList(board: Board) {
        Log.e(mTAG, "=== $robotName showPossibleBeatList start ===")

        Log.d(mTAG, "possibleCouldBeatList = $possibleCouldBeatList")

        for (i in 0 until possibleCouldBeatList.size) {
            print(board.getNameFromId(possibleCouldBeatList[i]))
            print("{")
            print(board.getCoordinateX(possibleCouldBeatList[i]))
            print(", ")
            print(board.getCoordinateY(possibleCouldBeatList[i]))
            print("}")
            println()
        }

        Log.e(mTAG, "=== $robotName showPossibleBeatList end ===")
    }

    fun showPossibleTakeDownList(board: Board) {
        Log.e(mTAG, "=== $robotName showPossibleTakeDownList start ===")

        print("possibleCouldBeatList = [")
        //Log.d(mTAG, "possibleCouldBeatList = $possibleCouldBeatList")
        for (i in 0 until possibleCouldBeatList.size) {
            print(board.getNameFromId(possibleCouldBeatList[i]))
            print("{")
            print(board.getCoordinateX(possibleCouldBeatList[i]))
            print(", ")
            print(board.getCoordinateY(possibleCouldBeatList[i]))
            print("}")
            if ( i != possibleCouldBeatList.size - 1)
                print(", ")
        }
        println("]")

        for (key in possibleCouldTakeDownList.keys) {

            val arrayList = possibleCouldTakeDownList[key]

            if (arrayList!!.size > 0) {
                print(board.getNameFromId(key))
                print("{")
                print(board.getCoordinateX(key))
                print(", ")
                print(board.getCoordinateY(key))
                print("}")
                print(" ==>")

                for (i in 0 until arrayList!!.size) {
                    print(" ")
                    print(board.getNameFromId(arrayList[i]))
                    print("{")
                    print(board.getCoordinateX(arrayList[i]))
                    print(", ")
                    print(board.getCoordinateY(arrayList[i]))
                    print("}")

                }

                println()
            }


        }



        Log.e(mTAG, "=== $robotName showPossibleTakeDownList end ===")
    }

    fun showPossibleLoseAfterTakeDownList(board: Board) {
        Log.e(mTAG, "=== $robotName showPossibleLoseAfterTakeDownList start ===")

        print("possibleCouldLostAfterBeat = [")
        //Log.d(mTAG, "possibleCouldBeatList = $possibleCouldBeatList")
        for (i in 0 until possibleCouldBeatList.size) {
            print(board.getNameFromId(possibleCouldBeatList[i]))
            print("{")
            print(board.getCoordinateX(possibleCouldBeatList[i]))
            print(", ")
            print(board.getCoordinateY(possibleCouldBeatList[i]))
            print("}")
            if ( i != possibleCouldBeatList.size - 1)
                print(", ")
        }
        println("]")

        for (key in possibleCouldLostAfterBeat.keys) {

            val arrayList = possibleCouldLostAfterBeat[key]

            if (arrayList!!.size > 0) {
                print(board.getNameFromId(key))
                print("{")
                print(board.getCoordinateX(key))
                print(", ")
                print(board.getCoordinateY(key))
                print("}")
                println(" ==>")

                for (i in 0 until arrayList.size) {
                    for (L2Key in arrayList[i].keys) {
                        print("   ")
                        print(board.getNameFromId(L2Key))
                        print("{")
                        print(board.getCoordinateX(L2Key))
                        print(", ")
                        print(board.getCoordinateY(L2Key))
                        print("}")
                        println(" <==")
                    }

                }

                println()
            }


        }

        Log.e(mTAG, "=== $robotName showPossibleLoseAfterTakeDownList end ===")
    }

    fun addChessToPossibleLostList(id: Int) {

        var found = false
        for (i in 0 until possibleCouldLostList.size) {
            if (id == possibleCouldLostList[i]) {
                found = true
                break
            }
        }

        if (!found) {
            possibleCouldLostList.add(id)
            possibleCouldLostList.sort()
        }
    }

    fun showPossibleLostList(board: Board) {
        Log.e(mTAG, "=== $robotName showPossibleLostList start ===")

        Log.d(mTAG, "possibleCouldLostList = $possibleCouldLostList")

        for (i in 0 until possibleCouldLostList.size) {
            print(board.getNameFromId(possibleCouldLostList[i]))
            print("{")
            print(board.getCoordinateX(possibleCouldLostList[i]))
            print(", ")
            print(board.getCoordinateY(possibleCouldLostList[i]))
            print("}")
            println()
        }

        Log.e(mTAG, "=== $robotName showPossibleLostList end ===")
    }

    fun isBeatLevelHigherThanLostLevel(board: Board): Boolean {
        Log.e(mTAG, "=== $robotName isBeatLevelHigherThanLostLevel start ===")

        var ret = false

        var getBeatFirst = 0
        var getLostFirst = 0

        Log.d(mTAG, "possibleCouldBeatList.size = ${possibleCouldBeatList.size})")

        if (possibleCouldBeatList.size > 0) {
            getBeatFirst = possibleCouldBeatList[0]

            if (possibleCouldLostList.size > 0) {
                getLostFirst = possibleCouldLostList[0]

                val chessBeat = board.getChessFromId(getBeatFirst)
                val chessLost = board.getChessFromId(getLostFirst)

                Log.d(mTAG, "chessBeat is ${board.getNameFromId(getBeatFirst)}, chessLost is ${board.getNameFromId(getLostFirst)}")

                ret = (chessBeat.level >= chessLost.level)
            } else {
                ret = true
            }

        } else {
            Log.e(mTAG, "Should move or show a new one.")
        }

        Log.e(mTAG, "=== $robotName isBeatLevelHigherThanLostLevel end ===")

        return ret
    }

    fun checkAliveListNeighbor(board: Board) {
        Log.e(mTAG, "=== checkAliveListNeighbor start ===")

        possibleCouldBeatList.clear()
        possibleCouldTakeDownList.clear()
        possibleCouldLostAfterBeat.clear()
        possibleCouldLostList.clear()

        if (aliveAndIsShowedList.size > 0) {
            for (i in 0 until  aliveAndIsShowedList.size) {
                val chess = board.getChessFromId(aliveAndIsShowedList[i])
                Log.d(mTAG, "chess.id = ${chess.id} name = ${board.getNameFromId(chess.id)} {${chess.coordinateX}, ${chess.coordinateY}}")
                val enemyList = board.findEnemyIsShowed(chess.id)
                enemyList.sort()
                Log.e(mTAG, "showed enemy = $enemyList")

                when(chess.id) {
                    10,11,26,27 -> { //cannon type
                        val canTakeDownList: ArrayList<Int> = ArrayList()

                        for (j in 0 until enemyList.size) {
                            val ret = chess.isCannonTarget(board.getCoordinateX(enemyList[j]), board.getCoordinateY(enemyList[j]), enemyList[j], board)
                            if (ret) {
                                Log.d(mTAG, "${board.getNameFromId(enemyList[j])}{${board.getCoordinateX(enemyList[j])}, ${board.getCoordinateY(enemyList[j])}} is the target of (cannon) ${board.getNameFromId(chess.id)}")

                                //add to possible beat list
                                addChessToPossibleBeatList(enemyList[j])
                                canTakeDownList.add(enemyList[j])

                                val possibleNeighborEnemy: ArrayList<Int> = board.findTargetNeighbor(chess.id, enemyList[j],board.getCoordinateX(enemyList[j]), board.getCoordinateY(enemyList[j]))

                                if (possibleNeighborEnemy.size > 0) {
                                    addChessToPossibleLostList(chess.id)
                                }

                                val possibleCannonEnemy: ArrayList<Int> = board.findTargetIsCannonTarget(chess.id, enemyList[j], board.getCoordinateX(enemyList[j]), board.getCoordinateY(enemyList[j]))

                                if (possibleCannonEnemy.size > 0) {
                                    addChessToPossibleLostList(chess.id)
                                }
                            }
                        }

                        possibleCouldTakeDownList[chess.id] = canTakeDownList
                    }
                    else -> { //not cannon

                        val canTakeDownList: ArrayList<Int> = ArrayList()
                        val takeDownCouldBeLostHashMapList: ArrayList<HashMap<Int, ArrayList<Int>>> = ArrayList()

                        for (j in 0 until enemyList.size) {
                            val ret = chess.isCanBeatTarget(chess.id, board.getCoordinateX(enemyList[j]), board.getCoordinateY(enemyList[j]), enemyList[j])
                            if (ret) {
                                val takeDownCouldBeLostHashMap: HashMap<Int, ArrayList<Int>> = HashMap()

                                Log.d(mTAG, "${board.getNameFromId(enemyList[j])}{${board.getCoordinateX(enemyList[j])}, ${board.getCoordinateY(enemyList[j])}} is the target of ${board.getNameFromId(chess.id)}")

                                ///add to possible beat list
                                addChessToPossibleBeatList(enemyList[j])
                                canTakeDownList.add(enemyList[j])


                                val possibleNeighborEnemy: ArrayList<Int> = board.findTargetNeighbor(chess.id, enemyList[j],board.getCoordinateX(enemyList[j]), board.getCoordinateY(enemyList[j]))
                                //private val possibleCouldLostAfterBeat: HashMap<Int, ArrayList< HashMap<Int, ArrayList<Int>> > > = HashMap()

                                if (possibleNeighborEnemy.size > 0) {
                                    addChessToPossibleLostList(chess.id)

                                    for (k in 0 until canTakeDownList.size) {
                                        takeDownCouldBeLostHashMap[canTakeDownList[k]] = possibleNeighborEnemy
                                    }
                                    takeDownCouldBeLostHashMapList.add(takeDownCouldBeLostHashMap)
                                }

                                val possibleCannonEnemy: ArrayList<Int> = board.findTargetIsCannonTarget(chess.id, enemyList[j], board.getCoordinateX(enemyList[j]), board.getCoordinateY(enemyList[j]))

                                if (possibleCannonEnemy.size > 0) {
                                    addChessToPossibleLostList(chess.id)
                                }
                            }
                        }

                        //
                        //possibleCouldTakeDownList.put(chess.id, canTakeDownList)
                        possibleCouldTakeDownList[chess.id] = canTakeDownList
                        possibleCouldLostAfterBeat[chess.id] = takeDownCouldBeLostHashMapList

                    }
                }


            }
        } else {
            Log.e(mTAG, "aliveAndIsShowedList.size = 0")
        }
        Log.e(mTAG, "=== checkAliveListNeighbor end ===")
    }
}