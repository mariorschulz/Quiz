package de.check24.quiz.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Quiz(
    val questions: List<Question>
)