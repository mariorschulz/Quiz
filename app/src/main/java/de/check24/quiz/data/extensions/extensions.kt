package de.check24.quiz.data.extensions

import de.check24.quiz.data.models.Question
import de.check24.quiz.data.models.Quiz
import de.check24.quiz.data.repositories.QuizRepositoryImpl

fun QuizRepositoryImpl.fallbackData(): Quiz {
    return Quiz(
        questions = listOf(
            Question(
                question = "Wann wurde CHECK24 gegründet?",
                answers = mapOf(
                    "A" to "1997",
                    "B" to "1999",
                    "C" to "2001",
                    "D" to "2004",
                ),
                questionImageUrl = null,
                correctAnswer = "B",
                score = 100,
            ),
            Question(
                question = "Wo befindet sich der CHECK24 Hauptsitz?",
                answers = mapOf(
                    "A" to "Berlin",
                    "B" to "Hamburg",
                    "C" to "München",
                    "D" to "Frankfurt",
                ),
                questionImageUrl = "https://app.check24.de/vg2-quiz/images/vg2_building_001.jpg",
                correctAnswer = "C",
                score = 100,
            ),
            Question(
                question = "Was ist keine Leitlinie von CHECK24??",
                answers = mapOf(
                    "A" to "Der Kunde ist König.",
                    "B" to "Der Kunde isst Könige.",
                ),
                questionImageUrl = "https://app.check24.de/vg2-quiz/images/vg2_family_001.jpg",
                correctAnswer = "B",
                score = 50,
            ),
        )
    )
}