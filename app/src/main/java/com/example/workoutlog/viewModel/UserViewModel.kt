package com.example.workoutlog.viewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutlog.models.LoginRequest
import com.example.workoutlog.models.LoginResponse
import com.example.workoutlog.models.RegisterRequest
import com.example.workoutlog.models.RegisterResponse
import com.example.workoutlog.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    val userRepository= UserRepository()
    var loginResponseLiveData=MutableLiveData<LoginResponse>()
    val loginErrorLiveData= MutableLiveData<String?>()
    val registerResponseLiveData= MutableLiveData<RegisterResponse>()
    val registerErrorLiveData =MutableLiveData<String?>()

    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response= userRepository.loginUser(loginRequest)
            if (response.isSuccessful){
                loginResponseLiveData.postValue(response.body())
            }
            else{
                val error=response.errorBody()?.string()
                loginErrorLiveData.postValue(error)
            }
        }
    }

    fun registerUser(registerRequest: RegisterRequest){
        viewModelScope.launch {
            val response= userRepository.registerUser(registerRequest)
            if (response.isSuccessful){
                registerResponseLiveData.postValue(response.body())
            }
            else{
                val error=response.errorBody()?.string()
                registerErrorLiveData.postValue(error)
            }
        }
    }

}