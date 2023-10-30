package de.check24.quiz.ui.views.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import de.check24.quiz.data.models.Question
import de.check24.quiz.ui.viewmodels.QuizAppViewModel
import de.check24.quiz.ui.viewmodels.Result

enum class GameState {
    WAITING, ANSWERED
}

@Composable
fun Game(
    viewModel: QuizAppViewModel,
    homeRoute: String,
    navController: NavHostController,
) {
    val question by viewModel.question.observeAsState()
    val currentIndex by viewModel.currentIndex.observeAsState()
    val countOfAnswered = (currentIndex ?: 0) + 1

    when (question) {
        is Result.Success -> DisplayQuestionScreen(
            (question as Result.Success<Question>).data,
            viewModel,
            countOfAnswered,
            navController,
            homeRoute
        )

        // App requirement, but it could display another screen to user in a production version
        is Result.Error -> navigateHomeAfterError(navController, homeRoute)

        // It could display something to user in a production version
        else -> navController.navigate(homeRoute)
    }
}

@Composable
fun DisplayQuestionScreen(
    questionData: Question,
    viewModel: QuizAppViewModel,
    countOfAnswered: Int?,
    navController: NavHostController,
    homeRoute: String
) {
    var state by remember { mutableStateOf(GameState.WAITING) }
    val countOfQuestions = viewModel.countOfQuestions

    val handleJumpToNextQuestion = {
        state = GameState.WAITING
    }

    val handleFinishing = {
        viewModel.saveUserHighestScore()
        navController.navigate(homeRoute)
    }

    QuestionScreen(
        state = state,
        countOfQuestions = countOfQuestions ?: 0,
        countOfAnswered = countOfAnswered ?: 0,
        currentScore = viewModel.score.value ?: 0,
        currentQuestion = questionData.question,
        currentQuestionImageUrl = questionData.questionImageUrl,
        currentQuestionScore = questionData.score,
        answers = questionData.answers,
        correctAnswer = questionData.correctAnswer,
    ) { score, isCorrect ->

        // handles onAnswer

        state = GameState.ANSWERED
        if (isCorrect) viewModel.updateUserScore(score)

        viewModel.loadNextQuestionOrFinish(
            onLoading = handleJumpToNextQuestion, onFinishing = handleFinishing
        )
    }
}

fun navigateHomeAfterError(navController: NavHostController, homeRoute: String) {
    navController.navigate(homeRoute)
}