package com.example.indianfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.indianfood.databinding.ActivityMainBinding
import com.example.indianfood.model.ContractActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var NavController: NavController = findNavController(R.id.fragmentContainerView2)
        var bottomnav = findViewById<BottomNavigationView>(R.id.bottomNavigationView2)
        bottomnav.setupWithNavController(NavController)
        binding.notificationButton.setOnClickListener {
            val bottomSheetDialog = Notifaction_Bottom_Fragment()
            bottomSheetDialog.show(supportFragmentManager, "Test")
        }
        binding.button.setOnClickListener {
            val intent = Intent(this, ContractActivity::class.java)
            startActivity(intent)
        }
    }
}