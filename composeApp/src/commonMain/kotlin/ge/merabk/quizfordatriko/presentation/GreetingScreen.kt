package ge.merabk.quizfordatriko.presentation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.stringResource
import quizfordatriko.composeapp.generated.resources.Res
import quizfordatriko.composeapp.generated.resources.greeting_text
import quizfordatriko.composeapp.generated.resources.greeting_title
import quizfordatriko.composeapp.generated.resources.start_quiz
import quizfordatriko.composeapp.generated.resources.view_all_questions

@Composable
fun GreetingScreenRoot(
    onStartQuizClicked: () -> Unit,
    onViewAllQuestionsClicked: () -> Unit
) {
    GreetingScreen(
        onAction = { action ->
            when (action) {
                is GreetingsAction.OnQuizStartClick -> onStartQuizClicked()
                is GreetingsAction.OnAllQuestionsStartClick -> onViewAllQuestionsClicked()
            }
        })
}

@Composable
private fun GreetingScreen(
    onAction: (GreetingsAction) -> Unit,
) {
    val backgroundColor = Color(0xFFF5E8C7)
    val infiniteTransition = rememberInfiniteTransition()

    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = -20f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.offset(y = animatedOffset.dp)
        ) {
            Text(
                text = stringResource(Res.string.greeting_title),
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3E2723)
                ),
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(Res.string.greeting_text),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF5D4037),
                    lineHeight = 22.sp
                ),
                textAlign = TextAlign.Center
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Button(
                    onClick = {
                        onAction(GreetingsAction.OnQuizStartClick)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A1B9A))
                ) {
                    Text(stringResource(Res.string.start_quiz), color = Color.White)
                }

                Button(
                    onClick = {
                        onAction(GreetingsAction.OnAllQuestionsStartClick)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0288D1))
                ) {
                    Text(stringResource(Res.string.view_all_questions), color = Color.White)
                }
            }
        }
    }
}
