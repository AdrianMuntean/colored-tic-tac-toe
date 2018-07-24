package controller

import ai.Strategy
import ai.StrategyFactory
import const.NONE
import const.PLAYER1
import const.PLAYER2
import const.RANDOM_STRATEGY
import javafx.scene.control.Label
import javafx.scene.layout.Background
import javafx.scene.text.Font
import views.MainView
import views.data.Backgrounds
import kotlin.properties.Delegates

class PlayerController(view: MainView, label: Label) {
    private val gameWinners = setOf(setOf(0, 1, 2), setOf(3, 4, 5), setOf(6, 7, 8), setOf(0, 3, 6), setOf(1, 4, 7), setOf(2, 5, 8), setOf(0, 4, 8), setOf(6, 4, 2))
    private var player1Turn = true
    private var cellsChosen = 0
    private var label: Label by Delegates.notNull()
    private var mainView: MainView by Delegates.notNull()
    private val player1Chose = mutableSetOf<Int>()
    private val player2Chose = mutableSetOf<Int>()

    private var aiController: Strategy? = null

    init {
        this.label = label
        this.mainView = view
    }

    private fun noWinner(): Boolean = cellsChosen == 9

    private fun endGame(winner: String) {
        cellsChosen = 0
        mainView.gameEnded(winner)
    }

    private fun updateSelection(cellName: Int): Boolean {
        val set = if (player1Turn) player1Chose else player2Chose
        set.add(cellName)

        if (gameWinner(set)) {
            println(currentPlayer() + " has won")
            label.text = currentPlayer() + " WON!!"
            label.fontProperty().set(Font.font(40.0))
            return true
        }

        return false
    }

    private fun cleanup() {
        player1Chose.clear()
        player2Chose.clear()
        player1Turn = true
        label.text = ""
        cellsChosen = 0
    }

    private fun gameWinner(set: MutableSet<Int>): Boolean {
        if (cellsChosen < 5) {
            return false
        }

        gameWinners.forEach {
            if (set.containsAll(it)) {
                return true
            }
        }

        return false
    }

    private fun updateLabel() {
        if (cellsChosen != 0) {
            this.label.text = currentPlayer() + " to pick"
        }
    }

    private fun currentPlayer(): String = if (player1Turn) PLAYER1 else PLAYER2

    fun clear() {
        cleanup()
    }

    fun updatePick(cellName: Int) {
        cellsChosen++
//        val color  = if (player1Turn) Backgrounds.clickedGreen() else Backgrounds.clickedRed()
        val gameWinner = updateSelection(cellName)
        if (gameWinner) {
            endGame(currentPlayer())
        }
        if (noWinner()) {
            endGame(NONE)
        }
        player1Turn = !player1Turn
        updateLabel()
    }

    fun getColor(): Background? = if (player1Turn) Backgrounds.clickedGreen() else Backgrounds.clickedRed()

    fun useAI(strategyName: String) {
        aiController = StrategyFactory.createStrategy(strategyName)
        TODO("implement AI Control")
    }
}