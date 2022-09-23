package com.example.workoutlog.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutlog.api.Exercisecatergory
import com.example.workoutlog.repository.ExerciseRepository
import kotlinx.coroutines.launch

class ExerciseViewModel :ViewModel(){
    val exerciseRepository = ExerciseRepository()
    val exerciseCategoryLiveData = MutableLiveData<List<Exercisecatergory>>()
    val errorLiveData = MutableLiveData<String?>()

    fun fetchExerciseCategories(accessToken : String){
        viewModelScope.launch {
            val response = exerciseRepository.fetchExercisesCategories(accessToken)
            if (response.isSuccessful){
                exerciseCategoryLiveData.postValue(response.body())
            }
            else{
                val errormsg = response.errorBody()?.string()
                errorLiveData.postValue(errormsg)
            }
        }
    }

}