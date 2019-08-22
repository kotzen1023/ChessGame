package com.seventhmoon.chessgame.fragment

import android.content.*
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*


import android.widget.TextView
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.gridlayout.widget.GridLayout
import com.seventhmoon.chessgame.MainActivity.Companion.actionBarSize
import com.seventhmoon.chessgame.MainActivity.Companion.height
import com.seventhmoon.chessgame.MainActivity.Companion.statusBarHeight
import com.seventhmoon.chessgame.MainActivity.Companion.width
import com.seventhmoon.chessgame.R
import com.seventhmoon.chessgame.data.Board
import com.seventhmoon.chessgame.data.Constants
import com.seventhmoon.chessgame.robot.SimpleRobot
import kotlinx.android.synthetic.main.fragment_gamepad_item.view.*


class FragmentGamePad : Fragment() {
    private val mTAG = FragmentGamePad::class.java.name
    private var gamePadContext: Context? = null
    private var gridLayout: GridLayout? = null
    private var dragIndex: Int = 0
    private var replaceIndex: Int = 0

    private var board: Board? = null

    private var mReceiver: BroadcastReceiver? = null
    private var isRegister = false

    private var isClickFirst = false

    var robot1: SimpleRobot? = null
    var robot2: SimpleRobot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e(mTAG, "onCreate")

        gamePadContext = context

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d(mTAG, "onCreateView")

        val view = inflater.inflate(R.layout.fragment_gamepad, container, false)

        gridLayout = view.findViewById<GridLayout>(R.id.gridLayout)

        val widthGridlayout = width
        val heightGridlayout = height - (statusBarHeight + actionBarSize)

        Log.e(mTAG, "widthGridlayout = $widthGridlayout, heightGridlayout = $heightGridlayout")



        board = Board(8, 4, gamePadContext as Context) // init board
        //init two robot
        robot1 = SimpleRobot("Robot1", gamePadContext as Context)
        robot2 = SimpleRobot("Robot2", gamePadContext as Context)


        for (i in 0 until board!!.getArraySize()) {
            val itemView = inflater.inflate(R.layout.fragment_gamepad_item, gridLayout, false)

            itemView.setOnLongClickListener {



                Log.e(mTAG, "tag = ${it.tag}")

                val data = ClipData.newPlainText("", "")
                val shadowBuilder = View.DragShadowBuilder(it)
                it.startDrag(data, shadowBuilder, view, 0)
                it.visibility = View.INVISIBLE

                dragIndex = it.tag as Int

                true
            }

            itemView.setOnClickListener {

                val chess = board!!.getChessFromId(board!!.getArrayItem(it.tag as Int))
                if (!chess.isShowed) {
                    chess.isShowed = true

                    //set color
                    gridLayout!![it.tag as Int].text.setTextColor(board!!.getColorFromId(chess.id))
                    //set name
                    gridLayout!![it.tag as Int].text.text = board!!.getNameFromId(chess.id)
                    gridLayout!!.invalidate()
                    Log.e(mTAG, "This chess id is ${chess.id}, it's ${board!!.getNameFromId(chess.id)}")

                    //detect user click

                    if (board!!.getMove() % 2 == 0) { //robot1's turn
                        robot1!!.showChess(chess.id, board!!.getMove())
                        robot1!!.showAliveList()
                    } else {
                        robot2!!.showChess(chess.id, board!!.getMove())
                        robot2!!.showAliveList()
                    }

                    board!!.saveCurrentState()
                } else {
                    Log.e(mTAG, "This chess was been showed.")
                }



            }

            var text = itemView.findViewById(R.id.text) as TextView
            text.setTextColor(Color.BLACK)
            text.text = "O"

            //text.setTextColor(getColorFromId(board!!.getArrayItem(i)))
            //text.text = getNameFromId(board!!.getArrayItem(i))

            itemView.tag = i
            gridLayout!!.addView(itemView)
        }

        val dragListener = DragListener()
        gridLayout!!.setOnDragListener(dragListener)


        val filter: IntentFilter

        mReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                if (intent.action != null) {
                    if (intent.action!!.equals(Constants.ACTION.ACTION_RESET_BOARD_ACTION, ignoreCase = true)) {
                        Log.d(mTAG, "ACTION_RESET_BOARD_ACTION")

                        board!!.resetShullfle()
                        for (i in 0 until gridLayout!!.size) {
                            //set color
                            gridLayout!![i].text.setTextColor(Color.BLACK)
                            //set name
                            gridLayout!![i].text.text = "O"
                        }

                        gridLayout!!.invalidate()
                        isClickFirst = false

                        robot1!!.reset()
                        robot2!!.reset()

                    } else if (intent.action!!.equals(Constants.ACTION.ACTION_SET_ROBOT2_KIND_AS_TRUE_ACTION, ignoreCase = true)) {
                        Log.d(mTAG, "ACTION_SET_ROBOT2_KIND_AS_TRUE_ACTION")

                        robot2!!.chooseKind = true

                    } else if (intent.action!!.equals(Constants.ACTION.ACTION_ROBOT_THINK_ACTION, ignoreCase = true)) {
                        Log.d(mTAG, "ACTION_ROBOT_THINK_ACTION")

                        if (board!!.getMove() % 2 == 0) {//robot1's turn
                            when(robot1!!.thinking())
                            {
                                1 -> {
                                    val clickIndex = board!!.chooseShowFromHidden()
                                    val chess = board!!.getChessFromId(board!!.getArrayItem(clickIndex))

                                    if (!chess.isShowed) {
                                        chess.isShowed = true

                                        //set color
                                        gridLayout!![clickIndex].text.setTextColor(board!!.getColorFromId(chess.id))
                                        //set name
                                        gridLayout!![clickIndex].text.text = board!!.getNameFromId(chess.id)

                                        gridLayout!!.invalidate()
                                    }
                                    Log.e(mTAG, "This chess id is ${chess.id}, it's ${board!!.getNameFromId(chess.id)}")

                                    robot1!!.showChess(chess.id, board!!.getMove())
                                    robot1!!.showAliveList()

                                    /*if (board!!.getMove() % 2 == 0) { //robot1's turn
                                        robot1!!.showChess(chess.id, board!!.getMove())
                                        robot1!!.showAliveList()
                                    } else { //robot2's turn
                                        robot2!!.showChess(chess.id, board!!.getMove())
                                        robot2!!.showAliveList()
                                    }*/
                                }
                                2 -> {
                                    Log.e(mTAG, "robot1 look for alive list")
                                    robot1!!.checkAliveListNeighbor(board as Board)
                                }
                            }
                        } else {
                            when(robot2!!.thinking())
                            {
                                1 -> {
                                    val clickIndex = board!!.chooseShowFromHidden()
                                    val chess = board!!.getChessFromId(board!!.getArrayItem(clickIndex))

                                    if (!chess.isShowed) {
                                        chess.isShowed = true

                                        //set color
                                        gridLayout!![clickIndex].text.setTextColor(board!!.getColorFromId(chess.id))
                                        //set name
                                        gridLayout!![clickIndex].text.text = board!!.getNameFromId(chess.id)

                                        gridLayout!!.invalidate()
                                    }
                                    Log.e(mTAG, "This chess id is ${chess.id}, it's ${board!!.getNameFromId(chess.id)}")

                                    robot2!!.showChess(chess.id, board!!.getMove())
                                    robot2!!.showAliveList()

                                    /*if (board!!.getMove() % 2 == 0) { //robot1's turn
                                        robot1!!.showChess(chess.id, board!!.getMove())
                                        robot1!!.showAliveList()
                                    } else { //robot2's turn
                                        robot2!!.showChess(chess.id, board!!.getMove())
                                        robot2!!.showAliveList()
                                    }*/
                                }
                                2 -> {
                                    Log.e(mTAG, "robot2 look for alive list")
                                    robot2!!.checkAliveListNeighbor(board as Board)
                                }
                            }
                        }





                        board!!.saveCurrentState()


                    } else if (intent.action!!.equals(Constants.ACTION.ACTION_SAVE_ID_TO_ANOTHER_ROBOT_ALIVE_LIST_ACTION, ignoreCase = true)) {
                        Log.d(mTAG, "ACTION_SAVE_ID_TO_ANOTHER_ROBOT_ALIVE_LIST_ACTION")

                        val id = intent.getIntExtra("ID", 0)
                        if (id < 17) { //red
                            if (!robot1!!.chooseKind) {
                                robot1!!.addChessToAliveList(id)
                            } else {
                                robot2!!.addChessToAliveList(id)
                            }
                        } else { //black
                            if (!robot1!!.chooseKind) { //red
                                robot2!!.addChessToAliveList(id)
                            } else {
                                robot1!!.addChessToAliveList(id)
                            }
                        }
                        robot1!!.showAliveList()
                        robot2!!.showAliveList()
                    } else if (intent.action!!.equals(Constants.ACTION.ACTION_SEND_ID_TO_ANOTHER_ROBOT_IN_ENEMY_LIST_ACTION, ignoreCase = true)) {
                        Log.d(mTAG, "ACTION_SEND_ID_TO_ANOTHER_ROBOT_IN_ENEMY_LIST_ACTION")

                        val id = intent.getIntExtra("ENEMY_ID", 0)
                        val kind = intent.getBooleanExtra("ENEMY_KIND", false)

                        if (robot1!!.chooseKind == kind) {
                            robot2!!.addChessToEnemyList(id)
                            robot2!!.showEnemyList()
                        } else {
                            robot1!!.addChessToEnemyList(id)
                            robot1!!.showEnemyList()
                        }
                    }
                }
            }
        }


        if (!isRegister) {
            filter = IntentFilter()
            filter.addAction(Constants.ACTION.ACTION_RESET_BOARD_ACTION)
            filter.addAction(Constants.ACTION.ACTION_SET_ROBOT2_KIND_AS_TRUE_ACTION)
            filter.addAction(Constants.ACTION.ACTION_ROBOT_THINK_ACTION)
            filter.addAction(Constants.ACTION.ACTION_SAVE_ID_TO_ANOTHER_ROBOT_ALIVE_LIST_ACTION)
            filter.addAction(Constants.ACTION.ACTION_SEND_ID_TO_ANOTHER_ROBOT_IN_ENEMY_LIST_ACTION)
            gamePadContext?.registerReceiver(mReceiver, filter)
            isRegister = true
            Log.d(mTAG, "registerReceiver mReceiver")
        }

        return view
    }

    override fun onDestroyView() {
        Log.i(mTAG, "onDestroy")

        if (isRegister && mReceiver != null) {
            try {
                gamePadContext!!.unregisterReceiver(mReceiver)
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            }

            isRegister = false
            mReceiver = null
            Log.d(mTAG, "unregisterReceiver mReceiver")
        }

        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.i(mTAG, "onActivityCreated")
        super.onActivityCreated(savedInstanceState)

    }

    inner class DragListener : View.OnDragListener {
        override fun onDrag(v: View, event: DragEvent): Boolean {
            val view = event.localState as View
            when (event.action) {
                DragEvent.ACTION_DRAG_LOCATION -> {
                    //Log.e(mTAG, "ACTION_DRAG_LOCATION")
                    // do nothing if hovering above own position
                    if (view === v) return true
                    // get the new list index
                    replaceIndex = calculateNewIndex(event.x, event.y)
                    Log.e(mTAG, "replaceIndex = $replaceIndex")

                    // remove the view from the old position
                    //gridLayout!!.removeView(view)
                    //gridLayout!!.removeViewAt(index)
                    // and push to the new
                    //gridLayout!!.addView(view, index)
                }
                DragEvent.ACTION_DROP -> {
                    Log.e(mTAG, "ACTION_DROP")
                    val chessSource = board!!.getChessFromId(board!!.getArrayItem(dragIndex))
                    val chessDest = board!!.getChessFromId(board!!.getArrayItem(replaceIndex))
                    if (!chessSource.isShowed || !chessDest.isShowed) {

                        Log.e(mTAG, "chessSource.isShowed = ${chessSource.isShowed}, chessDest.isShowed = ${chessDest.isShowed}")

                        if (chessDest.level > 0) { //if dest is a chess, not space
                            Log.e(mTAG, "This chess is not showed, do nothing")

                            gridLayout!!.getChildAt(dragIndex).visibility = View.VISIBLE
                        } else { //chessDest is space, become move
                            Log.e(mTAG, "dest level is 0. means move")
                            if (board!!.isNeighbor(dragIndex, replaceIndex)) {
                                Log.e(mTAG, "move from (x = ${chessSource.coordinateX}, y = ${chessSource.coordinateY}) to (x = ${board!!.getCoordinateX(replaceIndex)}, y = ${board!!.getCoordinateY(replaceIndex)})")
                                val temp = board!!.getArrayItem(dragIndex)

                                board!!.setArrayItem(dragIndex, 0)
                                gridLayout!![dragIndex].text.text = board!!.getArrayItem(replaceIndex).toString()

                                board!!.setArrayItem(replaceIndex, temp)
                                gridLayout!![replaceIndex].text.text = temp.toString()


                                //set color
                                gridLayout!![dragIndex].text.setTextColor(board!!.getColorFromId(board!!.getArrayItem(dragIndex)))
                                //set name
                                gridLayout!![dragIndex].text.text = board!!.getNameFromId(board!!.getArrayItem(dragIndex))

                                //set color
                                gridLayout!![replaceIndex].text.setTextColor(board!!.getColorFromId(temp))
                                //set name
                                gridLayout!![replaceIndex].text.text = board!!.getNameFromId(temp)

                                gridLayout!![dragIndex].visibility = View.VISIBLE

                                gridLayout!!.invalidate()


                            } else {
                                Log.e(mTAG, "Not neighbor")

                                gridLayout!!.getChildAt(dragIndex).visibility = View.VISIBLE
                            }


                        }

                        view.visibility = View.VISIBLE

                        board!!.refreshCoordinate()
                    } else {
                        if (replaceIndex == dragIndex) {
                            Log.e(mTAG, "The same position!!")
                            gridLayout!!.getChildAt(replaceIndex).visibility = View.VISIBLE
                        } else if (chessSource.kind == chessDest.kind) {
                            Log.e(mTAG, "kind is the same!")
                            gridLayout!!.getChildAt(dragIndex).visibility = View.VISIBLE
                        } else {

                            if (board!!.chessCouldBeReplace(chessSource, chessDest)) {



                                Log.e(mTAG, "replace new position!!")
                                Log.e(mTAG, "old gridLayout[$dragIndex] = ${board!!.getArrayItem(dragIndex)}")

                                Log.e(mTAG, "new gridLayout[$replaceIndex] = ${board!!.getArrayItem(replaceIndex)}")

                                //swap id
                                val temp = board!!.getArrayItem(dragIndex)

                                board!!.setArrayItem(dragIndex, 0)
                                gridLayout!![dragIndex].text.text = board!!.getArrayItem(replaceIndex).toString()

                                board!!.setArrayItem(replaceIndex, temp)
                                gridLayout!![replaceIndex].text.text = temp.toString()


                                //set color
                                gridLayout!![dragIndex].text.setTextColor(board!!.getColorFromId(board!!.getArrayItem(dragIndex)))
                                //set name
                                gridLayout!![dragIndex].text.text = board!!.getNameFromId(board!!.getArrayItem(dragIndex))

                                //set color
                                gridLayout!![replaceIndex].text.setTextColor(board!!.getColorFromId(temp))
                                //set name
                                gridLayout!![replaceIndex].text.text = board!!.getNameFromId(temp)

                                Log.e(mTAG, "gridLayout.size = "+gridLayout!!.size)




                                //gridLayout!!.addView(view, replaceIndex)

                                Log.d(mTAG, "arrayList = ${board!!.arrayList}")
                            }

                            gridLayout!![dragIndex].visibility = View.VISIBLE

                            gridLayout!!.invalidate()
                        }

                        view.visibility = View.VISIBLE

                        board!!.refreshCoordinate()
                    }
                }

                DragEvent.ACTION_DRAG_ENDED -> if (!event.result) {
                    Log.e(mTAG, "ACTION_DRAG_ENDED")
                    view.visibility = View.VISIBLE
                }
            }
            return true
        }
    }

    private fun calculateNewIndex(x: Float, y: Float): Int {
        // calculate which column to move to
        val cellWidth = gridLayout!!.width / gridLayout!!.columnCount
        val column = (x / cellWidth).toInt()

        // calculate which row to move to
        val cellHeight = gridLayout!!.height / gridLayout!!.rowCount
        val row = Math.floor((y / cellHeight).toDouble()).toInt()

        // the items in the GridLayout is organized as a wrapping list
        // and not as an actual grid, so this is how to get the new index
        var index = row * gridLayout!!.columnCount + column
        if (index >= gridLayout!!.childCount) {
            index = gridLayout!!.childCount - 1
        }

        return index
    }


}