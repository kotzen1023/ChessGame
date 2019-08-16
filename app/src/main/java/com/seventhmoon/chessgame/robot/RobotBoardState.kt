package com.seventhmoon.chessgame.robot

class RobotBoardState {
    var isMyTurn: Boolean = false
    var kind: Boolean = false //default kind is red
    var array: Array<IntArray> = Array(8) { IntArray(4) }

}