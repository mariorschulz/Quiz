package de.check24.quiz.data.repositories

import android.util.Log
import de.check24.quiz.data.extensions.fallbackData
import de.check24.quiz.data.models.Quiz
import de.check24.quiz.data.network.QuizService


class QuizRepositoryImpl(private val quizApiClient: QuizService) : QuizRepository {
    private val TAG = QuizRepositoryImpl::class.java.simpleName

    override suspend fun getQuiz(): Quiz {
        return try {
            quizApiClient.getQuiz()
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
            fallbackData()
        }
    }
}
