package com.example.workoutlog.api

import com.google.gson.annotations.SerializedName

data class Exercisecatergory(
    @SerializedName("catergory_id")var catergoryId:String,
    @SerializedName("catergory_name")var catergoryName:String
)
