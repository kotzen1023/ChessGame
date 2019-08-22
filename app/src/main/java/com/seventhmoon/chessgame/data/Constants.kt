package com.seventhmoon.chessgame.data

class Constants {
    class ACTION {
        companion object {
            const val ACTION_RESET_BOARD_ACTION : String = "com.seventhmoon.ChessGame.RestBoardAction"
            const val ACTION_ROBOT_THINK_ACTION : String = "com.seventhmoon.ChessGame.RobotThinkAction"
            const val ACTION_SET_ROBOT2_KIND_AS_TRUE_ACTION : String = "com.seventhmoon.ChessGame.SetRobot2KindAsTrueAction"
            const val ACTION_SAVE_ID_TO_ANOTHER_ROBOT_ALIVE_LIST_ACTION : String = "com.seventhmoon.ChessGame.SaveIdToAnotherRobotAliveListAction"
            const val ACTION_SEND_ID_TO_ANOTHER_ROBOT_IN_ENEMY_LIST_ACTION : String = "com.seventhmoon.ChessGame.SendIdToAnotherRobotInEnemyListAction"
        }
    }
}