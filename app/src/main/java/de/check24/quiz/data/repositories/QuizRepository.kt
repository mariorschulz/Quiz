package de.check24.quiz.data.repositories

import de.check24.quiz.data.models.Quiz

interface QuizRepository {
    suspend fun getQuiz(): Quiz
    // suspend fun saveQuizList(): Nothing = TODO()
}