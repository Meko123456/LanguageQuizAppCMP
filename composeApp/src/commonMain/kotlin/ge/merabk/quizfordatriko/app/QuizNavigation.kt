package ge.merabk.quizfordatriko.app


import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import ge.merabk.quizfordatriko.domain.Question
import ge.merabk.quizfordatriko.presentation.GreetingScreenRoot
import ge.merabk.quizfordatriko.presentation.quiz.QuizScreenRoot
import ge.merabk.quizfordatriko.presentation.quiz.QuizViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.quizGraph(navController: NavHostController) {
    navigation<Route.QuizGraph>(startDestination = Route.GreetingPage) {
        composableGreetingPage(navController)
        composableQuizPage(navController)
    }
}

fun NavGraphBuilder.composableGreetingPage(navController: NavHostController) {
    composable<Route.GreetingPage> {

        GreetingScreenRoot(
            onStartQuizClicked = {
                navController.navigate(Route.QuizPage)
            },
            //todo es shesacvlelia ro yvela shekitxvaze gagiyvanos
            onViewAllQuestionsClicked = {
                navController.navigate(Route.QuizPage)
            }
        )
    }
}


fun NavGraphBuilder.composableQuizPage(navController: NavHostController) {
    composable<Route.QuizPage> {
        QuizScreenRoot(
            onNextQuestion = {},
            onAnswerConfirmed = {},
            viewModel = QuizViewModel()
        )
    }
}


@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}