package de.check24.quiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import de.check24.quiz.ui.app.QuizApp
import de.check24.quiz.ui.viewmodels.QuizAppViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: QuizAppViewModel by viewModels {
        QuizAppViewModel.build(context = applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizApp(viewModel = viewModel, navController = rememberNavController())
        }
    }
}