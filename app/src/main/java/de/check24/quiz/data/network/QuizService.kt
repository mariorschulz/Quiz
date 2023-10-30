package de.check24.quiz.data.network

import de.check24.quiz.data.models.Quiz
import retrofit2.http.GET

interface QuizService {
    @GET("quiz.json")
    suspend fun getQuiz(): Quiz
}