package ge.merabk.quizfordatriko

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ge.merabk.quizfordatriko.app.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "QuizForDatriko",
    ) {
        App()
    }
}