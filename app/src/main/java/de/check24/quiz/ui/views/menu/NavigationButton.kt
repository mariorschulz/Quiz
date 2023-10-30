package de.check24.quiz.ui.views.menu

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import de.check24.quiz.ui.theme.QuizTheme
import de.check24.quiz.ui.views.shared.BaseButton

@Composable
fun NavigationButton(
    title: String,
    navigateTo: String,
    navController: NavHostController?,
    modifier: Modifier = Modifier,
) {
    BaseButton(
        text = title,
        textColor = MaterialTheme.colorScheme.onPrimary,
        borderColor = MaterialTheme.colorScheme.secondary,
        containerColor = MaterialTheme.colorScheme.secondary,
        modifier = modifier,
    ) {
        navController?.navigate(navigateTo)
    }
}

@Preview
@Composable
fun NavigationButtonPreview() {
    QuizTheme {
        NavigationButton(
            title = "start",
            navigateTo = "home",
            navController = null,
        )
    }
}