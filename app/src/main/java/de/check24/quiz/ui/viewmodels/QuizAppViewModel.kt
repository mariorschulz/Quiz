package de.check24.quiz.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import de.check24.quiz.data.domain.QuestionUseCase
import de.check24.quiz.data.models.Question
import de.check24.quiz.data.network.QuizApiClient
import de.check24.quiz.data.repositories.QuizRepositoryImpl
import de.check24.quiz.data.repositories.UserRepository
import de.check24.quiz.data.repositories.UserRepositoryImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// There is for sure a better place to put this peace of code, but for seeking of time
// it will be implemented here
sealed class Result<T> {
    class Success<T>(val data: T) : Result<T>()
    class Error<T>(val message: String? = "") : Result<T>()
    // class Loading<T> : Result<T>() Not implemented for seek of Time
}

private const val READ_TIMEOUT_MS = 2000L

class QuizAppViewModel(
    private val userRepository: UserRepository,
    private val questionUseCase: QuestionUseCase,
) : ViewModel() {

    // the list of questions
    private var _questionList = MutableLiveData<List<Question>>()
    val countOfQuestions: Int? get() = _questionList.value?.count()

    // keeps track of the questions that should be answered
    private var _currentIndex = MutableLiveData(0)
    val currentIndex: LiveData<Int> get() = _currentIndex

    // keeps track of the user score on actual game
    private var _score = MutableLiveData(0)
    val score: LiveData<Int> get() = _score

    // the user achieved highest score
    private var _highestScore = MutableLiveData(0)
    val highestScore: LiveData<Int> get() = _highestScore

    // current question to be answered
    private val _question = MutableLiveData<Result<Question>>()
    val question: LiveData<Result<Question>> get() = _question

    init {
        fetchQuiz()
        fetchUserData()
    }

    private fun fetchQuiz() {
        viewModelScope.launch {
            try {
                _questionList.value = questionUseCase.getQuestions()?.apply {
                    _question.value = Result.Success(this.first())
                }
            } catch (e: Exception) {
                _question.value = Result.Error("Error trying to get quiz data")
            }
        }
    }

    private fun fetchUserData() {
        viewModelScope.launch {
            try {
                val userScore = userRepository.getUserHighestScore()
                _highestScore.value = userScore
            } catch (e: Exception) {
                // should be better handled, for now just logging it out
                Log.d("ViewModel", "Exception fetching user data ${e.message.toString()}")
            }
        }
    }

    fun loadNextQuestionOrFinish(onLoading: () -> Unit, onFinishing: () -> Unit) {
        viewModelScope.launch {
            delay(READ_TIMEOUT_MS) // THIS WAS AN REQUIREMENT (Nach Anweisung der Probeaufgabe)

            // get new index
            val newIndex = (_currentIndex.value ?: 0) + 1

            // get next question
            val nextQuestion = _questionList.value?.getOrNull(newIndex)

            if (nextQuestion != null) {
                _currentIndex.value = newIndex
                _question.value = Result.Success(nextQuestion)

                // execute callback
                onLoading()
            } else {
                onFinishing()

                // For sure not the best practice here, but I have implemented here a small delay
                // to deal with the go back animation. The animation is a little bit slower than
                // the following reset procedure, what cause a not polished user experience
                delay(200)

                resetGame()
            }
        }
    }

    fun updateUserScore(newScore: Int) {
        val actualScore = _score.value ?: 0
        _score.value = actualScore + newScore
    }

    fun saveUserHighestScore() {
        viewModelScope.launch {
            val currentScore = _score.value ?: 0
            val currentHighestScore = _highestScore.value ?: 0

            if (currentHighestScore < currentScore) {
                _highestScore.value = currentScore
                userRepository.saveUserHighestScore(currentScore)
            }
        }
    }

    private fun resetGame() {
        _currentIndex.value = 0
        _score.value = 0

        // go to first question
        _questionList.value?.let {
            _question.value = Result.Success(it.first())
        }
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun build(context: Context): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {

                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return QuizAppViewModel(
                        userRepository = UserRepositoryImpl(context),
                        questionUseCase = QuestionUseCase(QuizRepositoryImpl(QuizApiClient.build()))
                    ) as T
                }
            }
        }
    }
}