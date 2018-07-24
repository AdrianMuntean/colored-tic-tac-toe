package views.data

import javafx.geometry.Insets
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Color

class Backgrounds {
    companion object {
        fun clickedRed() = Background(BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY))
        fun clickedGreen() = Background(BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY))
        fun gameEnded() = Background(BackgroundFill(Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY))
    }
}