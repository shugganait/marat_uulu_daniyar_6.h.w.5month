package com.kg.love_calculator_beta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.kg.love_calculator_beta.databinding.ActivityMainBinding
import com.kg.love_calculator_beta.preference.PrefHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor(private val prefH: PrefHelper): AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    constructor() : this()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        if (!prefH.isUserSeen()) {
            navController.navigate(R.id.onBoardFragment)
        }
    }
}