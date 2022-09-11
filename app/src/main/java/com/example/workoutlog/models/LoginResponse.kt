package com.example.workoutlog.models

import com.google.gson.annotations.SerializedName


data class LoginResponse(
    var message:String,
    @SerializedName("access_token") var accessToken:String,
    @SerializedName("user_Id")var userId: String,
    @SerializedName("profile_Id")var profileId: String
)