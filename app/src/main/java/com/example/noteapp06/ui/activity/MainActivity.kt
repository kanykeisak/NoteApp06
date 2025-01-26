package com.example.noteapp06.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.noteapp06.R
import com.example.noteapp06.databinding.ActivityMainBinding
import com.example.noteapp06.utils.PreferenceHelper

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val sharedPreferences = PreferenceHelper()
        sharedPreferences.unit(this)
        
        val navHostFragment = supportFragmentManager.findFragmentById(binding.fragmentContainer.id) as NavHostFragment
        navController = navHostFragment.navController
        
        if (sharedPreferences.onBoard) {
            navController.navigate(R.id.noteFragment)
        }
    }
}
