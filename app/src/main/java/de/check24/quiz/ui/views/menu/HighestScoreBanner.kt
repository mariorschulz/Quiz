package de.check24.quiz.ui.views.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.check24.quiz.R
import de.check24.quiz.ui.theme.QuizTheme

@Composable
fun HighestScoreBanner(score: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            color = MaterialTheme.colorScheme.onPrimary,
            text = stringResource(id = R.string.highscore)
        )
        Text(
            color = MaterialTheme.colorScheme.onPrimary,
            text = "$score ${stringResource(id = R.string.points)}"
        )
    }
}

@Preview
@Composable
fun HighestScoreBannerPreview() {
    QuizTheme {
        Surface(color = MaterialTheme.colorScheme.primary) {
            HighestScoreBanner(score = 500)
        }
    }
}
