package com.seventhmoon.chessgame.data

class Board {
    var rows: Int = 4
    var columns: Int = 8
    var boardSize = Array(rows) {Array(columns) {0}}
}