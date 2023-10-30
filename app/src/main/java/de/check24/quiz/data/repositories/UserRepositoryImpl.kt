package de.check24.quiz.data.repositories

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import okio.IOException

class UserRepositoryImpl(private val context: Context) : UserRepository {
    private val TAG = UserRepositoryImpl::class.java.simpleName

    override suspend fun getUserHighestScore(): Int {
        val userScore = context.dataStore.data
            // get data
            .map { preferences -> preferences[USER_SCORE] ?: 0 }

            // handle errors
            .catch { exception ->
                if (exception is IOException) {
                    Log.d(TAG, exception.message.toString())
                    // Here should be the exception better handled.
                    // Eventually should the user be notified about this error...
                } else {
                    throw exception
                }
            }

        return userScore.first()
    }

    override suspend fun saveUserHighestScore(score: Int) {
        context.dataStore.edit { preferences ->
            preferences[USER_SCORE] = score
        }
    }

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_data")
        private val USER_SCORE = intPreferencesKey(name = "score")
    }
}