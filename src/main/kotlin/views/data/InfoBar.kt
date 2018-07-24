package views.data

import javafx.scene.control.Label
import javafx.scene.text.Font

object InfoBar{
    val label = Label().apply {
        font = Font.font(20.0)
    }

    var text = ""
}

