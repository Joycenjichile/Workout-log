package com.example.workoutlog.api

import com.example.workoutlog.models.LoginRequest
import com.example.workoutlog.models.LoginResponse
import com.example.workoutlog.models.RegisterRequest
import com.example.workoutlog.models.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @POST("/Login")
    suspend fun login (@Body LoginRequest: LoginRequest): Response<LoginResponse>
    @GET("/exercises-category")
    suspend fun fetchExeciseCategories(@Header("Authorization")accessToken:String): Response<List<Exercisecatergory>>
}