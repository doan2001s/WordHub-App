package com.workhub.global

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.workhub.global.core.ui.BaseActivity
import com.workhub.global.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun createBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
        enableEdgeToEdge()
        val navHost = supportFragmentManager.findFragmentById(R.id.navHostFragment)
        as NavHostFragment
        val navController = navHost.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigation.visibility =
                if (destination.id == R.id.loginFragment) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
        }

        binding.bottomNavigation.setupWithNavController(navController)
        ViewCompat.setOnApplyWindowInsetsListener(
            binding.navHostFragment
        ) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }
    }
}
