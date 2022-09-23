package com.example.workoutlog.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.workoutlog.R
import com.example.workoutlog.Util.Constants
import com.example.workoutlog.databinding.ActivityHomeBinding
import com.example.workoutlog.viewModel.ExerciseViewModel


class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var sharedPref : SharedPreferences
    val exerciseViewModel:ExerciseViewModel by viewModels()
    lateinit var sharePref:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvLogout.setOnClickListener {
            val intent=Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            Logoutrequest()
        }

        casViews()
        setupBottomNav()
        sharedPref=getSharedPreferences(Constants.prefesFile, MODE_PRIVATE)
        val token=sharePref.getString(Constants.accessToken,Constants.EMPTY_STRIN)
        exerciseViewModel.fetchExerciseCategories(token!!)
    }

    override fun onResume() {
        super.onResume()
        exerciseViewModel.exerciseCategoryLiveData.observe(this, Observer { exerciseCategories ->
            Toast.makeText(this, "fetched ${exerciseCategories} category", Toast.LENGTH_LONG)
        })

    }


    fun casViews(){
        binding.fcvHome
        binding.bnvHome
    }
    fun setupBottomNav() {
        binding.bnvHome.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.plan -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, PlanFragment()).commit()
                    true
                }
                R.id.track -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, TrackFragment()).commit()
                    true
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, ProfileFragment()).commit()
                    true
                }
                else -> false
            }
        }

    }
    companion object{
        fun getIntent(context: Context):Intent{
            return  Intent(context,HomeActivity::class.java)
        }
    }

    fun Logoutrequest () {
        sharedPref.edit().clear().commit()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}