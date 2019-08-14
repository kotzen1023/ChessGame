package com.seventhmoon.chessgame.fragment

import android.content.ClipData
import android.content.Context
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
import kotlinx.android.synthetic.main.fragment_gamepad_item.view.*
import kotlin.random.Random







class FragmentGamePad : Fragment() {
    private val mTAG = FragmentGamePad::class.java.name
    private var gamePadContext: Context? = null
    private var gridLayout: GridLayout? = null
    private var dragIndex: Int = 0
    private var replaceIndex: Int = 0

    private var board: Board? = null

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

            var text = itemView.findViewById(R.id.text) as TextView
            //text.text = board!!.getArrayItem(i).toString()
            text.text = when (board!!.getArrayItem(i)) {
                1 -> {
                    getString(R.string.general_red)
                }
                2 -> getString(R.string.guard_red)
                3 -> getString(R.string.guard_red)
                4 -> getString(R.string.hand_red)
                5 -> getString(R.string.hand_red)
                6 -> getString(R.string.castle_red)
                7 -> getString(R.string.castle_red)
                else -> ""
            }

            itemView.tag = i
            gridLayout!!.addView(itemView)
        }

        val dragListener = DragListener()
        gridLayout!!.setOnDragListener(dragListener)


        return view
    }

    override fun onDestroyView() {
        Log.i(mTAG, "onDestroy")



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
                    Log.e(mTAG, "ACTION_DRAG_LOCATION")
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
                    if (replaceIndex == dragIndex) {
                        Log.e(mTAG, "The same position!!")
                        gridLayout!!.getChildAt(replaceIndex).visibility = View.VISIBLE
                    } else {
                        Log.e(mTAG, "replace new position!!")
                        Log.e(mTAG, "old gridLayout[$dragIndex] = ${board!!.getArrayItem(dragIndex)}")

                        Log.e(mTAG, "new gridLayout[$replaceIndex] = ${board!!.getArrayItem(replaceIndex)}")

                        val temp = board!!.getArrayItem(dragIndex)
                        board!!.setArrayItem(dragIndex, board!!.getArrayItem(replaceIndex))
                        gridLayout!![dragIndex].text.text = board!!.getArrayItem(replaceIndex).toString()
                        board!!.setArrayItem(replaceIndex, temp)
                        gridLayout!![replaceIndex].text.text = temp.toString()

                        Log.e(mTAG, "gridLayout.size = "+gridLayout!!.size)

                        gridLayout!![dragIndex].visibility = View.VISIBLE

                        gridLayout!!.invalidate()
                        //gridLayout!!.addView(view, replaceIndex)

                        Log.d(mTAG, "arrayList = ${board!!.arrayList}")
                    }




                    view.visibility = View.VISIBLE
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