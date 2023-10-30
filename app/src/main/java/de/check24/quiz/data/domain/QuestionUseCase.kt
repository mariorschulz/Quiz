package de.check24.quiz.data.domain

import de.check24.quiz.data.models.Question
import de.check24.quiz.data.repositories.QuizRepository

class QuestionUseCase(private val quizRepository: QuizRepository) {

    suspend fun getQuestions(): List<Question>? {
        val quiz = quizRepository.getQuiz()
        return quiz.questions.map { question: Question ->
            // Here should be used a new Question Type, but for seeking of simplicity it just
            // transforms the data
            Question(
                question = question.question,
                answers = question.answers.toList().shuffled().toMap(),
                questionImageUrl = question.questionImageUrl,
                correctAnswer = question.correctAnswer,
                score = question.score,
            )
        }
    }
}