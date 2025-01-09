package ge.merabk.quizfordatriko.presentation.quiz

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ge.merabk.quizfordatriko.data.QuestionsList
import ge.merabk.quizfordatriko.domain.Question
import kotlinx.coroutines.delay

@Composable
fun QuizScreenRoot(
    viewModel: QuizViewModel,
    onAnswerConfirmed: (Boolean) -> Unit,
    onNextQuestion: () -> Unit
) {
    QuizScreen(
        viewModel = viewModel,
        onConfirm = { isCorrect ->
            onAnswerConfirmed(isCorrect)
        },
        onNextQuestionClick = {
            onNextQuestion()
        }
    )
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun QuizScreen(
    viewModel: QuizViewModel,
    onConfirm: (Boolean) -> Unit,
    onNextQuestionClick: () -> Unit
) {


}
