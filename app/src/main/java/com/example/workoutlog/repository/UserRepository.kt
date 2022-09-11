package com.example.workoutlog.repository

import com.example.workoutlog.api.ApiClient
import com.example.workoutlog.api.ApiInterface
import com.example.workoutlog.models.LoginRequest
import com.example.workoutlog.models.RegisterRequest
import com.example.workoutlog.models.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient= ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun  loginUser(loginRequest: LoginRequest)= withContext(Dispatchers.IO){
        val response = apiClient.login(loginRequest)
        return@withContext response
    }
    suspend fun  registerUser(registerRequest: RegisterRequest):Response<RegisterResponse>
            = withContext(Dispatchers.IO){
        val response = apiClient.registerUser(registerRequest)
        return@withContext response
    }
}