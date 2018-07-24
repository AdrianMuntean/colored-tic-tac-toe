package views.data

import controller.PlayerController
import javafx.scene.control.Button
import javafx.scene.input.KeyCode
import tornadofx.onHover

class TicTacButton {

    companion object {
        fun newInstance(name: Int, controller: PlayerController) : Button {
            val button = Button().apply {
                prefHeight = 200.0
                prefWidth = 200.0
            }

            button.setOnMouseClicked {
                clickAction(name, button, controller)
            }

            button.setOnKeyPressed {
                if (it.code == KeyCode.ENTER) {
                    clickAction(name, button, controller)
                }
            }

            button.onHover {
            }

            return button
        }

        private fun clickAction(name: Int, button: Button, controller: PlayerController) {
//            println("Cliked on $name")
            if (!button.disabledProperty().value) {
                button.background = controller.getColor()
                button.disableProperty().set(true)
                controller.updatePick(name)
            }
        }
    }

}