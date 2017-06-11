package com.siziksu.kotlinTicTacToe.view.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.siziksu.kotlinTicTacToe.R
import com.siziksu.kotlinTicTacToe.presenter.main.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG: String = "MainActivity"

    val presenter: MainPresenter by lazy { MainPresenter() }
    val count: Int by lazy { mainContainer.childCount }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mainToolbar)
        mainStatistics.text = presenter.getStatistics()
    }

    fun mainGameButtonClick(view: View) {
        presenter.imageButtonClick(view, { value ->
            run {
                if (value) {
                    mainReplayButton.visibility = View.VISIBLE
                    setImageButtonsEnable(false)
                    mainStatistics.text = presenter.getStatistics()
                }
            }
        })
    }

    fun mainReplayButtonClick(view: View) {
        presenter.buttonClick(view)
        mainReplayButton.visibility = View.GONE
        setImageButtonsEnable(true)
    }

    private fun setImageButtonsEnable(value: Boolean) {
        (0..count - 1)
                .filter { mainContainer.getChildAt(it) is ImageView }
                .forEach { mainContainer.getChildAt(it).isEnabled = value }
    }
}