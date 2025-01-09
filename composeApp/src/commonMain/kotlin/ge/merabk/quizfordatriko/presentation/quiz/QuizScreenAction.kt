package ge.merabk.quizfordatriko.presentation.quiz

sealed interface QuizScreenAction {
    data class SelectOption(val index: Int) : QuizScreenAction
    data object ConfirmAnswer : QuizScreenAction
    data object ProceedToNextQuestion : QuizScreenAction
    data class UpdateTimeRemaining(val seconds: Int) : QuizScreenAction
}
