package com.siziksu.kotlinTicTacToe.view.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.siziksu.kotlinTicTacToe.R
import com.siziksu.kotlinTicTacToe.presenter.main.MainPresenter
import kotlinx.android.synthetic.main.activity_tic_tac_toe.*

class MainActivity : AppCompatActivity() {

    val TAG: String = "MainActivity"

    val presenter: MainPresenter by lazy { MainPresenter() }
    val count: Int by lazy { container.childCount }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe)
        setSupportActionBar(defaultToolbar)
        statistics.text = presenter.getStatistics()
    }

    fun imageButtonClick(view: View) {
        presenter.imageButtonClick(view, { value ->
            run {
                if (value) {
                    button_replay.visibility = View.VISIBLE
                    setImageButtonsEnable(false)
                    statistics.text = presenter.getStatistics()
                }
            }
        })
    }

    fun buttonClick(view: View) {
        presenter.buttonClick(view)
        button_replay.visibility = View.GONE
        setImageButtonsEnable(true)
    }

    private fun setImageButtonsEnable(value: Boolean) {
        (0..count - 1)
                .filter { container.getChildAt(it) is ImageView }
                .forEach { container.getChildAt(it).isEnabled = value }
    }
}