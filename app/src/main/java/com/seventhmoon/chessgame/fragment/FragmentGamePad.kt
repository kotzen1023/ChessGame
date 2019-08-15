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



        board = Board(8, 4) // init board

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
                    gridLayout!![it.tag as Int].text.setTextColor(getColorFromId(chess.id))
                    //set name
                    gridLayout!![it.tag as Int].text.text = getNameFromId(chess.id)

                    gridLayout!!.invalidate()
                }
                Log.e(mTAG, "This chess id is ${chess.id}, it's ${getNameFromId(chess.id)}")
            }

            var text = itemView.findViewById(R.id.text) as TextView
            text.setTextColor(Color.BLACK)
            text.text = "o"

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
                            gridLayout!![i].text.text = "o"
                        }

                        gridLayout!!.invalidate()
                    }
                }
            }
        }


        if (!isRegister) {
            filter = IntentFilter()
            filter.addAction(Constants.ACTION.ACTION_RESET_BOARD_ACTION)
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
                                gridLayout!![dragIndex].text.setTextColor(getColorFromId(board!!.getArrayItem(dragIndex)))
                                //set name
                                gridLayout!![dragIndex].text.text = getNameFromId(board!!.getArrayItem(dragIndex))

                                //set color
                                gridLayout!![replaceIndex].text.setTextColor(getColorFromId(temp))
                                //set name
                                gridLayout!![replaceIndex].text.text = getNameFromId(temp)

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
                                gridLayout!![dragIndex].text.setTextColor(getColorFromId(board!!.getArrayItem(dragIndex)))
                                //set name
                                gridLayout!![dragIndex].text.text = getNameFromId(board!!.getArrayItem(dragIndex))

                                //set color
                                gridLayout!![replaceIndex].text.setTextColor(getColorFromId(temp))
                                //set name
                                gridLayout!![replaceIndex].text.text = getNameFromId(temp)

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

    private fun getNameFromId(index: Int): String {
        return when(index) {
            1 -> getString(R.string.general_red)
            2 -> getString(R.string.guard_red)
            3 -> getString(R.string.guard_red)
            4 -> getString(R.string.hand_red)
            5 -> getString(R.string.hand_red)
            6 -> getString(R.string.castle_red)
            7 -> getString(R.string.castle_red)
            8 -> getString(R.string.knight_red)
            9 -> getString(R.string.knight_red)
            10 -> getString(R.string.cannon_red)
            11 -> getString(R.string.cannon_red)
            12 -> getString(R.string.soldier_red)
            13 -> getString(R.string.soldier_red)
            14 -> getString(R.string.soldier_red)
            15 -> getString(R.string.soldier_red)
            16 -> getString(R.string.soldier_red)
            17 -> getString(R.string.general_blue)
            18 -> getString(R.string.guard_blue)
            19 -> getString(R.string.guard_blue)
            20 -> getString(R.string.hand_blue)
            21 -> getString(R.string.hand_blue)
            22 -> getString(R.string.castle_blue)
            23 -> getString(R.string.castle_blue)
            24 -> getString(R.string.knight_blue)
            25 -> getString(R.string.knight_blue)
            26 -> getString(R.string.cannon_blue)
            27 -> getString(R.string.cannon_blue)
            28 -> getString(R.string.soldier_blue)
            29 -> getString(R.string.soldier_blue)
            30 -> getString(R.string.soldier_blue)
            31 -> getString(R.string.soldier_blue)
            32 -> getString(R.string.soldier_blue)
            else -> ""
        }
    }

    private fun getColorFromId(index: Int): Int {

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
}