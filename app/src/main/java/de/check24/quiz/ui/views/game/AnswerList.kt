package de.check24.quiz.ui.views.game

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import de.check24.quiz.ui.theme.QuizTheme

@Composable
fun AnswerList(
    state: GameState,
    answers: Map<String, String>,
    correctAnswer: String,
    score: Int,
    onAnswer: (score: Int, isCorrect: Boolean) -> Unit
) {
    var clickedAnswer by remember { mutableStateOf("") }

    Column {
        answers.forEach {
            val isCorrect = it.key == correctAnswer

            AnswerButton(
                text = it.value,
                state = state,
                isCorrect = isCorrect,
                clicked = clickedAnswer == it.key,
                enabled = state == GameState.WAITING
            ) {
                clickedAnswer = it.key
                onAnswer(score, isCorrect)
            }
        }
    }
}

@Preview
@Composable
fun AnswerListPreview() {
    val onAnswer: (score: Int, isCorrect: Boolean) -> Unit = { _, _ -> }
    val correctAnswer = "B"
    val score = 100
    val answers = mapOf(
        "A" to "This is an answer A",
        "B" to "This is an answer B",
        "C" to "This is an answer C",
        "D" to "This is an answer D",
    )

    QuizTheme {
        Surface {
            Column {
                Column {
                    Text("GameScreenState.WAITING")
                    AnswerList(
                        state = GameState.WAITING,
                        answers = answers,
                        correctAnswer = correctAnswer,
                        score = score,
                        onAnswer = onAnswer
                    )
                }

                Column {
                    Text("GameScreenState.ANSWERED")
                    AnswerList(
                        state = GameState.ANSWERED,
                        answers = answers,
                        correctAnswer = correctAnswer,
                        score = score,
                        onAnswer = onAnswer
                    )
                }
            }
        }
    }
}