package ge.merabk.quizfordatriko.presentation.quiz

import ge.merabk.quizfordatriko.domain.Question

data class QuizScreenState(
    val currentQuestion: Question? = null,
    val selectedOptionIndex: Int? = null,
    val isAnswerConfirmed: Boolean = false,
    val questionCounter: String = "0/0",
    val timeRemaining: String? = null, // In seconds
    val isCorrect: Boolean? = null,
)

