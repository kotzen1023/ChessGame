package com.seventhmoon.chessgame.fragment

import android.content.*
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button


import android.widget.TextView
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.gridlayout.widget.GridLayout
import com.seventhmoon.chessgame.MainActivity
import com.seventhmoon.chessgame.MainActivity.Companion.actionBarSize
import com.seventhmoon.chessgame.MainActivity.Companion.height
import com.seventhmoon.chessgame.MainActivity.Companion.statusBarHeight
import com.seventhmoon.chessgame.MainActivity.Companion.width
import com.seventhmoon.chessgame.R
import com.seventhmoon.chessgame.data.Board
import com.seventhmoon.chessgame.data.Constants
import com.seventhmoon.chessgame.robot.RobotBoardState
import com.seventhmoon.chessgame.robot.SimpleRobot
import kotlinx.android.synthetic.main.fragment_gamepad_item.view.*
import kotlin.random.Random







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
        robot1 = SimpleRobot("Robot1")
        robot2 = SimpleRobot("Robot2")
        robot1!!.boardInitState(board!!.boardArray)
        robot2!!.boardInitState(board!!.boardArray)

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
                }
                Log.e(mTAG, "This chess id is ${chess.id}, it's ${board!!.getNameFromId(chess.id)}")

                //detect user click
                if (!isClickFirst) {
                    if (chess.id < 17) { //robot1 is red, robot2 is blue
                        robot1!!.firstShow(chess.id, false, board!!.boardArray, true)
                        robot2!!.firstShow(chess.id, true, board!!.boardArray, false)

                    } else { //robot1 is blue, robot2 is red
                        robot1!!.firstShow(chess.id, true, board!!.boardArray, true)
                        robot2!!.firstShow(chess.id, false, board!!.boardArray, false)

                    }

                    isClickFirst = true
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

                        robot1!!.reset(board!!.boardArray)
                        robot2!!.reset(board!!.boardArray)

                    } else if (intent.action!!.equals(Constants.ACTION.ACTION_RANDOM_SELECT_ACTION, ignoreCase = true)) {



                        if (robot1!!.state == SimpleRobot.CurrentState.STATE_INIT && robot2!!.state == SimpleRobot.CurrentState.STATE_INIT) {
                            val clickIndex = board!!.chooseShowFromHidden()

                            if (clickIndex > -1) {
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

                                //detect user click
                                if (!isClickFirst) {
                                    if (chess.id < 17) { //robot1 is red, robot2 is blue
                                        robot1!!.firstShow(chess.id, false, board!!.boardArray, true)
                                        robot2!!.firstShow(chess.id, true, board!!.boardArray, false)

                                    } else { //robot1 is blue, robot2 is red
                                        robot1!!.firstShow(chess.id, true, board!!.boardArray, true)
                                        robot2!!.firstShow(chess.id, false, board!!.boardArray, false)

                                    }
                                    isClickFirst = true
                                }
                            }
                        } else { // state = RUNNING
                            val robot1State = robot1!!.stateStack.peek() //see previous state
                            //val robot2State = robot2!!.stateStack.peek()

                            if (robot1State.isMyTurn) { //should be robot2's turn

                                val ret = robot2!!.thinking(board as Board)
                                when(ret) {
                                    1 -> {
                                        val selectIndex = robot2!!.randomChoose(board as Board)

                                        if (selectIndex > -1) {
                                            var stateRobot1 = robot2!!.stateStack.peek()
                                            stateRobot1.isMyTurn = false
                                            stateRobot1.kind = robot1!!.chooseKind
                                            robot1!!.stateStack.push(stateRobot1)

                                            gridLayout!![selectIndex].text.setTextColor(board!!.getColorFromId(board!!.getArrayItem(selectIndex)))
                                            //set name
                                            gridLayout!![selectIndex].text.text = board!!.getNameFromId(board!!.getArrayItem(selectIndex))

                                            gridLayout!!.invalidate()
                                        }
                                    }
                                    2 -> {
                                        Log.e(mTAG, "robot2->2")
                                    }
                                }


                            } else { //should be robot1's turn
                                val ret = robot1!!.thinking(board as Board)

                                when(ret) {
                                    1 -> {
                                        val selectIndex = robot1!!.randomChoose(board as Board)

                                        if (selectIndex > -1) {
                                            var stateRobot2 = robot1!!.stateStack.peek()
                                            stateRobot2.isMyTurn = false
                                            stateRobot2.kind = robot2!!.chooseKind
                                            robot2!!.stateStack.push(stateRobot2)

                                            gridLayout!![selectIndex].text.setTextColor(board!!.getColorFromId(board!!.getArrayItem(selectIndex)))
                                            //set name
                                            gridLayout!![selectIndex].text.text = board!!.getNameFromId(board!!.getArrayItem(selectIndex))

                                            gridLayout!!.invalidate()
                                        }
                                    }
                                    2 -> {
                                        Log.e(mTAG, "robot1->2")
                                    }
                                }
                            }
                        }




                    }
                }
            }
        }


        if (!isRegister) {
            filter = IntentFilter()
            filter.addAction(Constants.ACTION.ACTION_RESET_BOARD_ACTION)
            filter.addAction(Constants.ACTION.ACTION_RANDOM_SELECT_ACTION)
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