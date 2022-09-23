package com.example.workoutlog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.workoutlog.databinding.ActivitySignupBinding
import com.example.workoutlog.models.RegisterRequest
import com.example.workoutlog.viewModel.UserViewModel


class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {
            validateSign()

        }
        binding.tvLogin.setOnClickListener {
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    fun validateSign() {
        var name = binding.etFirstName.text.toString()
        var name2 = binding.etLastName.text.toString()
        var phone = binding.etPhoneNumber.text.toString()
        var mail = binding.etmail.text.toString()
        var pass = binding.etpassword.text.toString()
        var confirm = binding.etConfirmPassword.text.toString()

        var error= false
        if (name.isBlank()) {
            binding.tilFirstName.error = "firstname_required"
        }
        if (name2.isBlank()) {
            error =true
            binding.tilLastName.error = "Last name required"
        }
        if (pass.isBlank()) {
            error =true
            binding.tilPhoneNumber.error = "number_required"
        }

        if (mail.isBlank()) {
            error =true
            binding.tilEmail.error = "email_required"
        }
        if (phone.isBlank()) {
            error =true
            binding.tilPassword.error = "password_required"

        if (confirm.isBlank()) {
            error =true
            binding.tilConfirmPassword.error = "confirmpassword_required"
        }
        if (pass!= confirm) {
            error =true
            binding.tilPassword.error= "password do not match"
        }
        if(!error){
            val  registerRequest= RegisterRequest(name, name2, mail, phone, pass)
//            makeRegistrationRequest(registerRequest)
            userViewModel.registerUser(registerRequest)
        }
    }

      fun onResume() {
        super.onResume()
        userViewModel.registerResponseLiveData.observe(this, Observer {
                signupResponse->
            Toast.makeText(baseContext,signupResponse.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext, LoginActivity::class.java))
        })

        userViewModel.registerErrorLiveData.observe(this, Observer {
                signupError->
            Toast.makeText(baseContext,signupError,Toast.LENGTH_LONG).show()
        })
    }}
}
