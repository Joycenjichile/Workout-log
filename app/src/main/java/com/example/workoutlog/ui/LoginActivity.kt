package com.example.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.workoutlog.databinding.ActivityLoginBinding
import com.example.workoutlog.models.LoginRequest
import com.example.workoutlog.models.LoginResponse
import com.example.workoutlog.viewModel.UserViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding
    lateinit var  sharedPrefs: SharedPreferences
    val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs=getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            val intent=Intent(this, HomeActivity::class.java)
            startActivity(intent)
            validateLogin()
        }

        binding.tvSignup.setOnClickListener {
            val intent=Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer { loginResponse->
            saveLoginDetails(loginResponse!!)
            Toast.makeText(baseContext,loginResponse?.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext, HomeActivity::class.java))
            finish()
        })
        userViewModel.loginErrorLiveData.observe(this, Observer { error->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
        })


    }



    fun validateLogin(){
        var email=binding.etemail.text.toString()
        var password =binding.etPassword.text.toString()
        var error=false

        if (email.isBlank()){
            binding.tilEmail.error="email_required"
            error=true
        }
        if (password.isBlank()){
            binding.tilPassword.error="Password required"
            error=true
        }
        if(!error){
            var loginRequest= LoginRequest(email,password)
            binding.pbLogin.visibility= View.VISIBLE
            userViewModel.loginUser(loginRequest)

        }
    }


    fun saveLoginDetails(LoginResponse: LoginResponse){
        val editor= sharedPrefs.edit()
        editor.putString("ACCESS_TOKEN", LoginResponse.accessToken)
        editor.putString("USER_ID",LoginResponse.userId)
        editor.putString("PROFILE_ID",LoginResponse.profileId)
        editor.apply()
    }
}