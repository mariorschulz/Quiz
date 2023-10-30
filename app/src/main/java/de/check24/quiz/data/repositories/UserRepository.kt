package de.check24.quiz.data.repositories

interface UserRepository {
    suspend fun getUserHighestScore(): Int
    suspend fun saveUserHighestScore(score: Int)
}