package com.android.rent4less.ui.views

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.android.rent4less.R
import com.android.rent4less.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        bottomNavView.setupWithNavController(navController)

        binding.menuIcon.setOnClickListener {
            logOut()
        }
    }

    private fun logOut() {
        AlertDialog.Builder(this)
            .setMessage("Like to exit app?")
            .setPositiveButton("Yes") {
                    dialog, _ ->
                dialog.dismiss()
                finish()
            }.setNegativeButton("No") {
                    dialog, _ -> dialog.dismiss()
            }.show()
    }
}