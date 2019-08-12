package com.seventhmoon.chessgame.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.seventhmoon.chessgame.R
import com.seventhmoon.chessgame.data.Board
import kotlin.random.Random

class FragmentGamePad : Fragment() {
    private val mTAG = FragmentGamePad::class.java.name
    private var gamePadContext: Context? = null

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

        val board = Board(8, 4) // init board

        for (i in 0 until 8) {
            for (j in 0 until 4) {

                
            }

        }






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
}