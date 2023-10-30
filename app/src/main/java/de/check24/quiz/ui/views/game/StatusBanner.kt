package de.check24.quiz.ui.views.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.check24.quiz.R
import de.check24.quiz.ui.theme.QuizTheme

@Composable
fun StatusBanner(quizCount: Int, answeredQuizCount: Int, currentScore: Int) {
    val scoreStatus = "${stringResource(id = R.string.current_score)}: $currentScore "
    val quizStatus = "${stringResource(id = R.string.quiz_status)} $answeredQuizCount / $quizCount"

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(8.dp)
    ) {
        Text(
            text = "$quizStatus - $scoreStatus",
            color = MaterialTheme.colorScheme.tertiary,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview
@Composable
fun StatusBannerPreview() {
    QuizTheme {
        StatusBanner(quizCount = 10, answeredQuizCount = 5, currentScore = 500)
    }
}