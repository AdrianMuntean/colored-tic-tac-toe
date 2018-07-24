package views

import const.*
import controller.PlayerController
import javafx.geometry.Insets
import javafx.scene.control.*
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.layout.FlowPane
import javafx.scene.paint.Color
import javafx.scene.text.Font
import tornadofx.*
import views.data.InfoBar
import views.data.TicTacButton

class MainView : View("Tic-tac-toe") {
    override val root = FlowPane()
    private val controller = PlayerController(this, InfoBar.label)
    private val twoPlayersButton = ButtonType("2 players", ButtonBar.ButtonData.YES)
    private val onePlayerWithAI = ButtonType("AI", ButtonBar.ButtonData.NO)

    init {
        super.setWindowMaxSize(617, 700)
        super.setWindowMinSize(617, 700)
        with(root) {
            prefWidth = 600.0
            prefHeight = 650.0
            background = Background(BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY))
            addContent(this)
        }
    }

    private fun addResetButton(flowPane: FlowPane) {
        flowPane += Label("                                                                                   ")
        val resetButton = Button("Reset").apply {
            prefHeight = 25.0
            prefWidth = 50.0
            paddingTop = 7
            paddingBottom = 7
            background = Background(BackgroundFill(Color.CHOCOLATE, CornerRadii.EMPTY, Insets.EMPTY))
        }
        resetButton.setOnMouseEntered {
            InfoBar.text = InfoBar.label.text
            InfoBar.label.text = "Press to reset"
        }
        resetButton.setOnMouseExited {
            InfoBar.label.text = InfoBar.text
        }
        resetButton.setOnMouseClicked {
            restartGame()
        }
        flowPane += resetButton


    }

    private fun addInfoBar(flowPane: FlowPane) {
        InfoBar.label.fontProperty().set(Font.font(20.0))
        InfoBar.label.text = "$PLAYER1 to begin"
        flowPane += InfoBar.label
        flowPane += Label("                                                                                                                              ")
    }

    private fun addButtons(flowPane: FlowPane) {
        flowPane += TicTacButton.newInstance(NW, controller)
        flowPane += TicTacButton.newInstance(N, controller)
        flowPane += TicTacButton.newInstance(NE, controller)
        flowPane += TicTacButton.newInstance(CW, controller)
        flowPane += TicTacButton.newInstance(C, controller)
        flowPane += TicTacButton.newInstance(CE, controller)
        flowPane += TicTacButton.newInstance(SW, controller)
        flowPane += TicTacButton.newInstance(S, controller)
        flowPane += TicTacButton.newInstance(SE, controller)
    }

    private fun showGameModeAlert() {
        alert(Alert.AlertType.CONFIRMATION, "2 players or with computer?", "Only 2 players supported at the moment  ", actionFn = { btnType ->
            if (btnType.buttonData == ButtonBar.ButtonData.NO) {
                controller.useAI(RANDOM_STRATEGY)
            }
        }, buttons = *arrayOf(twoPlayersButton/*, onePlayerWithAI*/))
    }

    fun gameEnded(winningPlayer: String) {
        if (winningPlayer == NONE) {
            alert(Alert.AlertType.CONFIRMATION, "Game has ended with no winner!", "", actionFn = { btnType ->
                if (btnType.buttonData == ButtonBar.ButtonData.OK_DONE) {
                    resetGame()
                }
            })
        } else {
            alert(Alert.AlertType.CONFIRMATION, "Game has ended. \"The winner is $winningPlayer\"", "", actionFn = { btnType ->
                if (btnType.buttonData == ButtonBar.ButtonData.OK_DONE) {
                    resetGame()
                }
            })
        }
    }

    private fun restartGame() {
        println("Restarting the game!")
        root.clear()
        controller.clear()
        addContent(root)
    }

    private fun resetGame() {
        println("Resetting the game!")
        root.clear()
        controller.clear()
        addBaseContent(root)
    }

    private fun addContent(flowPane: FlowPane) {
        addBaseContent(flowPane)
        showGameModeAlert()
    }

    private fun addBaseContent(flowPane: FlowPane) {
        addButtons(flowPane)
        addInfoBar(flowPane)
        addResetButton(flowPane)
    }
}