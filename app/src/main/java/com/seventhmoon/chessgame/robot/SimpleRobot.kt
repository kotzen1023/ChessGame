package com.seventhmoon.chessgame.robot

import android.util.Log
import com.seventhmoon.chessgame.data.Board
import java.util.*
import kotlin.collections.ArrayList

class SimpleRobot(name: String) {
    private val mTAG = SimpleRobot::class.java.name
    var robotName: String = ""
    var chooseKind: Boolean = false //default is red
    val stateStack: Stack<RobotBoardState> = Stack()
    val aliveAndIsShowedList: ArrayList<Int> = ArrayList()
    var state: CurrentState = CurrentState.STATE_NONE

    enum class CurrentState {
        STATE_NONE, STATE_INIT, STATE_RUNNING
    }

    init {
        robotName = name
        //push in first shuffle board
        //chooseKind = kind

        /*val initState = RobotBoardState()
        initState.isMyTurn = turn
        initState.kind = chooseKind
        initState.array = arrayList
        stateStack.push(initState)*/

        Log.d(mTAG, "=== SimpleRobot: ($robotName) was created ===")

        /*for (i in 0 until stateStack.peek().array.size) {
            for (j in 0 until stateStack.peek().array[i].size) {
                print(initState.array[i][j])
                if (j%4 != 3)
                    print(", ")
            }
            println()
        }*/
        Log.d(mTAG, "=== SimpleRobot: ($robotName) end ===")
        state = CurrentState.STATE_INIT
    }

    fun reset() {
        Log.d(mTAG, "=== reset === start")
        //robotName = name
        //chooseKind = kind
        //clear stack
        stateStack.clear()
        aliveAndIsShowedList.clear()

        /*val initState = RobotBoardState()
        initState.isMyTurn = turn
        initState.kind = chooseKind
        initState.array = arrayList
        stateStack.push(initState)*/

        Log.d(mTAG, "=== SimpleRobot: ($robotName) was reset ===")

        /*for (i in 0 until initState.array.size) {
            for (j in 0 until initState.array[i].size) {
                print(initState.array[i][j])
                if (j%4 != 3)
                    print(", ")
            }
            println()
        }*/
        Log.d(mTAG, "=== SimpleRobot: ($robotName) end ===")
        state = CurrentState.STATE_INIT
    }

    fun firstShow(id: Int, kind: Boolean, arrayList: Array<IntArray>, turn: Boolean) {

        Log.e(mTAG, "=== $robotName firstShow start: id = $id, kind = $kind, turn = $turn === ")

        aliveAndIsShowedList.add(id)
        chooseKind = kind

        val initState = RobotBoardState()
        initState.isMyTurn = turn
        initState.kind = chooseKind
        initState.array = arrayList
        stateStack.push(initState)

        for (i in 0 until stateStack.peek().array.size) {
            for (j in 0 until stateStack.peek().array[i].size) {
                print(stateStack.peek().array[i][j])
                if (j%4 != 3)
                    print(", ")
            }
            println()
        }

        if (turn)
            aliveAndIsShowedList.add(id)

        state = CurrentState.STATE_RUNNING

        Log.e(mTAG, "=== $robotName firstShow end === ")
    }

    fun randomChoose(board: Board): Int {
        //var ret: Int = 0

        val selectIndex = board.chooseShowFromHidden()

        if (selectIndex > -1) {
            val chess = board.getChessFromId(board.getArrayItem(selectIndex))
            if (!chess.isShowed) {
                chess.isShowed = true

                val currentState = RobotBoardState()
                currentState.isMyTurn = true
                currentState.kind = chooseKind
                currentState.array = board.boardArray
                stateStack.push(currentState)
            }
            Log.e(mTAG, "This chess id is ${chess.id}, it's ${board.getNameFromId(chess.id)}")
        }

        return selectIndex
    }

    fun thinking() {
        //find a chess you can take down opponent

    }
}