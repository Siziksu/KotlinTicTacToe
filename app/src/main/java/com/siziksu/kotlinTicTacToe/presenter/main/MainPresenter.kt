package com.siziksu.kotlinTicTacToe.presenter.main

import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import com.siziksu.kotlinTicTacToe.R
import com.siziksu.kotlinTicTacToe.common.model.Winner

class MainPresenter {

    companion object {
        private const val TAG: String = "MainPresenter"
        private const val DRAW: Int = 0
        private const val FIRST: Int = 1
        private const val SECOND: Int = 2
    }

    private var currentPlayer: Int = FIRST
    private var player1 = HashMap<Int, View>()
    private var player2 = HashMap<Int, View>()
    private val stats = Array(3) { 0 }
    private var moves: Int = 0

    fun imageButtonClick(view: View, callback: (Boolean) -> Unit) {
        moves++
        val drawable: Int
        when (currentPlayer) {
            FIRST -> {
                drawable = R.drawable.cell_x
                player1.put(getCell(view), view)
                currentPlayer = SECOND
            }
            SECOND -> {
                drawable = R.drawable.cell_o
                player2.put(getCell(view), view)
                currentPlayer = FIRST
            }
            else -> return
        }
        view.isEnabled = false
        (view as ImageView).setImageDrawable(ContextCompat.getDrawable(view.context, drawable))
        val winner: Winner? = checkWinner()
        when (winner?.player) {
            FIRST -> {
                stats[FIRST] = stats[FIRST] + 1
                callback.invoke(true)
                winner.winnerValues.forEach { it ->
                    (player1[it] as ImageView).setImageDrawable(ContextCompat.getDrawable(view.context, R.drawable.cell_x_win))
                }
            }
            SECOND -> {
                stats[SECOND] = stats[SECOND] + 1
                callback.invoke(true)
                winner.winnerValues.forEach { it ->
                    (player2[it] as ImageView).setImageDrawable(ContextCompat.getDrawable(view.context, R.drawable.cell_o_win))
                }
            }
            else -> {
                if (moves == 9) {
                    stats[DRAW] = stats[DRAW] + 1
                    callback.invoke(true)
                } else {
                    callback.invoke(false)
                }
            }
        }
    }

    private fun checkWinner(): Winner? {
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) return Winner(FIRST, intArrayOf(1, 2, 3))
        else if (player1.contains(4) && player1.contains(5) && player1.contains(6)) return Winner(FIRST, intArrayOf(4, 5, 6))
        else if (player1.contains(7) && player1.contains(8) && player1.contains(9)) return Winner(FIRST, intArrayOf(7, 8, 9))
        else if (player1.contains(1) && player1.contains(4) && player1.contains(7)) return Winner(FIRST, intArrayOf(1, 4, 7))
        else if (player1.contains(2) && player1.contains(5) && player1.contains(8)) return Winner(FIRST, intArrayOf(2, 5, 8))
        else if (player1.contains(3) && player1.contains(6) && player1.contains(9)) return Winner(FIRST, intArrayOf(3, 6, 9))
        else if (player1.contains(1) && player1.contains(5) && player1.contains(9)) return Winner(FIRST, intArrayOf(1, 5, 9))
        else if (player1.contains(7) && player1.contains(5) && player1.contains(3)) return Winner(FIRST, intArrayOf(7, 5, 3))
        else if (player2.contains(1) && player2.contains(2) && player2.contains(3)) return Winner(SECOND, intArrayOf(1, 2, 3))
        else if (player2.contains(4) && player2.contains(5) && player2.contains(6)) return Winner(SECOND, intArrayOf(4, 5, 6))
        else if (player2.contains(7) && player2.contains(8) && player2.contains(9)) return Winner(SECOND, intArrayOf(7, 8, 9))
        else if (player2.contains(1) && player2.contains(4) && player2.contains(7)) return Winner(SECOND, intArrayOf(1, 4, 7))
        else if (player2.contains(2) && player2.contains(5) && player2.contains(8)) return Winner(SECOND, intArrayOf(2, 5, 8))
        else if (player2.contains(3) && player2.contains(6) && player2.contains(9)) return Winner(SECOND, intArrayOf(3, 6, 9))
        else if (player2.contains(1) && player2.contains(5) && player2.contains(9)) return Winner(SECOND, intArrayOf(1, 5, 9))
        else if (player2.contains(7) && player2.contains(5) && player2.contains(3)) return Winner(SECOND, intArrayOf(7, 5, 3))
        else return Winner(0, intArrayOf())
    }

    private fun getCell(view: View): Int {
        when (view.id) {
            R.id.mainGameButton1 -> return 1
            R.id.mainGameButton2 -> return 2
            R.id.mainGameButton3 -> return 3
            R.id.mainGameButton4 -> return 4
            R.id.mainGameButton5 -> return 5
            R.id.mainGameButton6 -> return 6
            R.id.mainGameButton7 -> return 7
            R.id.mainGameButton8 -> return 8
            R.id.mainGameButton9 -> return 9
            else -> return 0
        }
    }

    fun buttonClick(view: View) {
        when (view.id) {
            R.id.mainReplayButton -> replay()
            else -> return
        }
    }

    private fun replay() {
        player1.forEach { it ->
            (it.value as ImageView).setImageDrawable(ContextCompat.getDrawable(it.value.context, android.R.color.transparent))
            it.value.isEnabled = true
        }
        player1.clear()
        player2.forEach { it ->
            (it.value as ImageView).setImageDrawable(ContextCompat.getDrawable(it.value.context, android.R.color.transparent))
            it.value.isEnabled = true
        }
        player2.clear()
        currentPlayer = FIRST
        moves = 0
    }

    fun getStatistics(): String {
        val total = stats[DRAW] + stats[FIRST] + stats[SECOND]
        return "TOTAL GAMES PLAYED: $total\n" +
                "PLAYER 1 WINS: " + stats[FIRST] + "\n" +
                "PLAYER 2 WINS: " + stats[SECOND] + "\n" +
                "DRAW GAMES: " + stats[DRAW]
    }
}
