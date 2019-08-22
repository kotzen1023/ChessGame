package com.seventhmoon.chessgame.data

class BoardState(move: Int, arrayList: Array<IntArray>) {
    var move: Int = 0
    var array: Array<IntArray> = Array(8) { IntArray(4) }

    init {
        this.move = move
        this.array = arrayList
    }
}