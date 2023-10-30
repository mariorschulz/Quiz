package de.check24.quiz.ui.views.menu

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import de.check24.quiz.R
import de.check24.quiz.ui.theme.QuizTheme
import de.check24.quiz.ui.viewmodels.QuizAppViewModel
import de.check24.quiz.ui.views.shared.BaseScreen

@Composable
fun MenuScreen(viewModel: QuizAppViewModel, navigateTo: String, navController: NavHostController?) {
    val userHighestScore by viewModel.highestScore.observeAsState()
    MenuScreenContent(
        highestScore = userHighestScore ?: 0,
        navigateTo = navigateTo,
        navController = navController
    )
}

@Composable
private fun MenuScreenContent(
    highestScore: Int, navigateTo: String, navController: NavHostController?
) {
    BaseScreen {
        Title()
        HighestScoreBanner(score = highestScore)
        Spacer(modifier = Modifier.padding(4.dp))
        NavigationButton(
            title = stringResource(id = R.string.start_button),
            navigateTo = navigateTo,
            navController = navController
        )
    }
}

@Preview
@Composable
fun MenuScreenPreview() {
    QuizTheme {
        MenuScreenContent(500, "", null)
    }
}