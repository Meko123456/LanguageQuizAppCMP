package ge.merabk.quizfordatriko.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object QuizGraph: Route

    @Serializable
    data object GreetingPage: Route

    @Serializable
    data object QuizPage : Route

    @Serializable
    data object AllQuestionsPage : Route
}