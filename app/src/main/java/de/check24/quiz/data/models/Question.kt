package de.check24.quiz.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Question(
    val question: String,
    val answers : Map<String, String>,
    val questionImageUrl: String?,
    val correctAnswer: String,
    val score: Int,
)