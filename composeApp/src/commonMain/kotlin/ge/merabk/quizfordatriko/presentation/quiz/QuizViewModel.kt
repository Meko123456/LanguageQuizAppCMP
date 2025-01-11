package ge.merabk.quizfordatriko.presentation.quiz

import androidx.lifecycle.ViewModel
import ge.merabk.quizfordatriko.data.QuestionsList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuizViewModel : ViewModel() {
    private val questions = QuestionsList.questions
    private var currentQuestionIndex = 0

    private val _state = MutableStateFlow(
        QuizScreenState(
            currentQuestion = questions.firstOrNull(),
            questionCounter = "1/${questions.size}",
            timeRemaining = "30"
        )
    )
    val state: StateFlow<QuizScreenState> = _state.asStateFlow()

    fun selectOption(index: Int) {
        _state.update {
            it.copy(selectedOptionIndex = index)
        }
    }

    fun confirmAnswer() {
        _state.update {
            it.copy(
                isAnswerConfirmed = true
            )
        }
    }

    fun proceedToNextQuestion(newQuestion: QuizScreenState) {
        _state.update {
            newQuestion
        }
    }

    fun updateTimeRemaining(seconds: Int) {
        _state.value = _state.value.copy(timeRemaining = seconds.toString())
    }

    fun onAction(action: QuizScreenAction) {
        when (action) {
            is QuizScreenAction.OnOptionSelected -> {
                _state.update { currentState ->
                    currentState.copy(selectedOptionIndex = action.index)
                }
            }

            QuizScreenAction.OnConfirmAnswer -> {
                val isCorrect =
                    _state.value.selectedOptionIndex == _state.value.currentQuestion?.correctOption
                _state.update { currentState ->
                    currentState.copy(
                        isAnswerConfirmed = true,
                        isCorrect = isCorrect
                    )
                }
            }

            QuizScreenAction.OnNextQuestion -> {
                moveToNextQuestion()
            }

        }
    }

    private fun moveToNextQuestion() {
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            _state.update { currentState ->
                currentState.copy(
                    currentQuestion = questions[currentQuestionIndex],
                    questionCounter = "${currentQuestionIndex + 1}/${questions.size}",
                    timeRemaining = "30",
                    selectedOptionIndex = null,
                    isAnswerConfirmed = false,
                    isCorrect = null
                )
            }
        } else {
            // Logic for end of quiz (e.g., show a results screen)
            // You can also use _state.update to modify a field like `isQuizFinished` if needed.
        }
    }
}
