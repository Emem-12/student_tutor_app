package com.example.fhenixproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fhenixproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_view, OnboardingFragment())
        fragmentTransaction.commit()
    }
}