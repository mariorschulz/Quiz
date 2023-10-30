package de.check24.quiz.ui.views.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.check24.quiz.ui.theme.QuizTheme
import de.check24.quiz.ui.views.shared.BaseScreen

@Composable
fun QuestionScreen(
    state: GameState,
    countOfQuestions: Int,
    countOfAnswered: Int,
    currentScore: Int,
    currentQuestion: String,
    currentQuestionImageUrl: String?,
    currentQuestionScore: Int,
    answers: Map<String, String>,
    correctAnswer: String,
    onAnswer: (score: Int, isCorrect: Boolean) -> Unit
) {

    BaseScreen(verticalArrangement = Arrangement.Top) {
        Spacer(modifier = Modifier.padding(48.dp)) // Header

        StatusBanner(
            quizCount = countOfQuestions,
            answeredQuizCount = countOfAnswered,
            currentScore = currentScore
        )

        QuestionCard(
            question = currentQuestion,
            imageUrl = currentQuestionImageUrl,
            questionScore = currentQuestionScore,
        )

        AnswerList(
            state = state,
            answers = answers,
            correctAnswer = correctAnswer,
            score = currentQuestionScore,
        ) { score, isCorrect ->
            onAnswer(score, isCorrect)
        }
    }
}

@Preview
@Composable
fun QuestionScreenPreview() {
    QuizTheme {
        QuestionScreen(state = GameState.WAITING,
            countOfQuestions = 10,
            countOfAnswered = 4,
            currentScore = 1000,
            currentQuestion = "A question ?",
            currentQuestionImageUrl = null,
            currentQuestionScore = 2000,
            answers = mapOf(
                "A" to "This is an answer A",
                "B" to "This is an answer B"
            ),
            correctAnswer = "A",
            onAnswer = { _, _ -> })
    }
}