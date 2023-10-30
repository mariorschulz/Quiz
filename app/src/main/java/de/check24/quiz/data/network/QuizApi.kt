package de.check24.quiz.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://app.check24.de/vg2-quiz/"

object QuizApiClient {
    fun build(): QuizService {
        val serializeEngine = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(serializeEngine).asLenient())
            .build()
            .create(QuizService::class.java)
    }
}