package ge.merabk.quizfordatriko.presentation.quiz

sealed interface QuizScreenAction {
    data class OnOptionSelected(val index: Int) : QuizScreenAction
    data object OnConfirmAnswer : QuizScreenAction
    data object OnNextQuestion : QuizScreenAction
}
