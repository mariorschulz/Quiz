package de.check24.quiz.ui.views.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import de.check24.quiz.R
import de.check24.quiz.ui.theme.QuizTheme
import de.check24.quiz.ui.views.shared.BaseScreen

@Composable
private fun ImageContainer(imageUrl: String?) {
    val contentScale = ContentScale.Fit
    val contentDescription = stringResource(id = R.string.question_image_description)

    Box(
        modifier = Modifier
            .height(200.dp)
            .width(200.dp)
            .padding(8.dp)
    ) {
        if (imageUrl != null && imageUrl != "null") {
            AsyncImage(
                model = imageUrl,
                contentDescription = contentDescription,
                contentScale = contentScale,
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.fallback),
                contentDescription = contentDescription,
                contentScale = contentScale,
            )
        }
    }
}

@Composable
fun QuestionCard(imageUrl: String?, questionScore: Int, question: String) {
    // a divergent from Theme color variant
    val green = Color.Green

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 32.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(color = green, text = "$questionScore ${stringResource(id = R.string.points)}")
        ImageContainer(imageUrl = imageUrl)
        Box(modifier = Modifier.padding(16.dp)) {
            Text(
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                text = question,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview
@Composable
fun QuestionCardPreview() {
    val questionScore = 300
    val question = "Wo befindet sich der CHECK24 Hauptsitz?"

    QuizTheme {
        BaseScreen {
            QuestionCard(null, questionScore, question)
        }
    }
}