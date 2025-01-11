package ge.merabk.quizfordatriko.presentation.quiz

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.resources.stringResource
import quizfordatriko.composeapp.generated.resources.Res
import quizfordatriko.composeapp.generated.resources.confirm_answer
import quizfordatriko.composeapp.generated.resources.correct
import quizfordatriko.composeapp.generated.resources.incorrect


@Composable
fun QuizScreenRoot(
    viewModel: QuizViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    QuizScreen(
        state = state,
        onAction = { action ->
            viewModel.onAction(action = action)
        }
    )
}


@Composable
private fun QuizScreen(
    state: QuizScreenState,
    onAction: (QuizScreenAction) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(bottom = 16.dp)
            .statusBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${stringResource(Res.string.correct)} ${state.questionCounter}",
                style = MaterialTheme.typography.bodyMedium
            )
            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xFFEAEAFF),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                if (state.isAnswerConfirmed) {
                    Text(
                        text = if (state.isCorrect == true) stringResource(Res.string.correct)
                        else stringResource(Res.string.incorrect),
                        color = if (state.isCorrect == true) Color.Green else Color.Red,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                } else {

                    Text(
                        text = state.timeRemaining.orEmpty(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF6563FF)
                    )
                }
            }
        }

        Text(
            text = state.currentQuestion?.question.orEmpty(),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            val options = listOf(
                state.currentQuestion?.optionOne.orEmpty(),
                state.currentQuestion?.optionTwo.orEmpty(),
                state.currentQuestion?.optionThree.orEmpty(),
                state.currentQuestion?.optionFour.orEmpty()
            )
            var selectedIndex by remember { mutableStateOf(-1) }

            options.forEachIndexed { index, option ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = if (selectedIndex == index) Color(0xFFEEF0FF) else Color.Transparent,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(
                            width = 2.dp,
                            color = if (selectedIndex == index) Color(0xFF6563FF) else Color.LightGray,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable(
                            indication = null,
                            interactionSource = remember {
                                MutableInteractionSource()
                            }
                        ) {
                            selectedIndex = index
                            onAction(QuizScreenAction.OnOptionSelected(index))
                        }
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = option)
                }
            }
        }

        Button(
            onClick = {
                if (!state.isAnswerConfirmed) {
                    onAction(QuizScreenAction.OnConfirmAnswer)
                } else {
                    onAction(QuizScreenAction.OnNextQuestion)
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6563FF))
        ) {
            Text(
                text = if (!state.isAnswerConfirmed) stringResource(Res.string.confirm_answer)
                else stringResource(Res.string.confirm_answer),
                color = if (!state.isAnswerConfirmed) MaterialTheme.colorScheme.onPrimary
                else MaterialTheme.colorScheme.primary
            )
        }
    }
}

