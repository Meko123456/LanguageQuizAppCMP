package ge.merabk.quizfordatriko.presentation

sealed interface GreetingsAction {

    data object OnQuizStartClick : GreetingsAction

    data object OnAllQuestionsStartClick : GreetingsAction

}