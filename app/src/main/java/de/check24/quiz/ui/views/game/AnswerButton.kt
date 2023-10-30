package de.check24.quiz.ui.views.game

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import de.check24.quiz.ui.theme.QuizTheme
import de.check24.quiz.ui.views.shared.BaseButton

@Composable
fun AnswerButton(
    text: String,
    state: GameState,

    // TODO: Maybe a class for it ...
    isCorrect: Boolean,
    clicked: Boolean,
    enabled: Boolean,

    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    var borderColor = Color.White

    if (state == GameState.ANSWERED) {
        if (isCorrect) {
            borderColor = Color.Green
        } else if (clicked) {
            borderColor = Color.Red
        }
    }

    BaseButton(
        text = text,
        textColor = MaterialTheme.colorScheme.onBackground,
        textUppercase = false,
        borderColor = borderColor,
        containerColor = MaterialTheme.colorScheme.onPrimary,
        enabled = enabled,
        modifier = modifier,
        onClick = onClick
    )
}

@Preview
@Composable
fun AnswerButton() {
    QuizTheme {
        Column {

            AnswerButton(
                text = "This is an answer",
                state = GameState.WAITING,
                isCorrect = false,
                clicked = false,
                enabled = true,
            ) {}

            AnswerButton(
                text = "This is a right answer",
                state = GameState.ANSWERED,
                isCorrect = true,
                clicked = true,
                enabled = true,
            ) {}

            AnswerButton(
                text = "This is a false answer",
                state = GameState.ANSWERED,
                isCorrect = false,
                clicked = true,
                enabled = true,
            ) {}
        }
    }
}