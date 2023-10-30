package de.check24.quiz.ui.views.shared

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.check24.quiz.ui.theme.QuizTheme

@Composable
fun BaseButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    textUppercase: Boolean = true,
    borderColor: Color = MaterialTheme.colorScheme.primary,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor, disabledContainerColor = containerColor
        ),
        shape = RoundedCornerShape(corner = CornerSize(4.dp)),
        border = BorderStroke(2.dp, borderColor),
        enabled = enabled,
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .fillMaxWidth()
    ) {
        Text(text = if (textUppercase) text.uppercase() else text, color = textColor)
    }
}

@Preview
@Composable
fun BaseButtonPreview() {
    QuizTheme {
        BaseButton(text = "click me", textColor = MaterialTheme.colorScheme.onPrimary, onClick = {})
    }
}