package com.example.workoutlog.repository

import com.example.workoutlog.api.ApiClient
import com.example.workoutlog.api.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseRepository {
    val apiClient= ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun fetchExercisesCategories(accessToken:String)=
        withContext(Dispatchers.IO){
            return@withContext apiClient.fetchExeciseCategories(accessToken)
        }
}