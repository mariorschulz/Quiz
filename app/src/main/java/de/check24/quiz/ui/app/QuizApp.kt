package de.check24.quiz.ui.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.check24.quiz.ui.navigation.Route
import de.check24.quiz.ui.theme.QuizTheme
import de.check24.quiz.ui.viewmodels.QuizAppViewModel
import de.check24.quiz.ui.views.game.Game
import de.check24.quiz.ui.views.menu.MenuScreen

@Composable
fun QuizApp(viewModel: QuizAppViewModel, navController: NavHostController) {
    QuizTheme {
        NavHost(
            navController = navController, startDestination = Route.MenuScreen.name
        ) {
            composable(Route.MenuScreen.name) {
                MenuScreen(
                    viewModel = viewModel,
                    navigateTo = Route.GameScreen.name,
                    navController = navController
                )
            }

            composable(Route.GameScreen.name) {
                Game(
                    viewModel = viewModel,
                    homeRoute = Route.MenuScreen.name,
                    navController = navController,
                )
            }
        }
    }
}