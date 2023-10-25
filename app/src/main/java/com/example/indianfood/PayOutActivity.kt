package com.example.indianfood


import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.example.indianfood.databinding.ActivityPayOutBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class PayOutActivity : AppCompatActivity() {


    lateinit var binding: ActivityPayOutBinding

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var latitude: TextView
    private lateinit var longtitude: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPayOutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.PlaceMyOrder.setOnClickListener {
            val bottomSheetDeialog = CongratsBottomSheet()
            bottomSheetDeialog.show(supportFragmentManager, "Test")
        }
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        latitude = findViewById(R.id.latitude)
        longtitude = findViewById(R.id.longtitude)
        val button= findViewById<Button>(R.id.buttonlocation)
        button.setOnClickListener {
            getLocation()
        }
    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),100)
            return

        }
        val location = fusedLocationProviderClient.lastLocation
        location.addOnSuccessListener {
            if (it != null){
                val textLatitude = "Latitude  " + it.latitude.toString()
                val textLongitude = "Longitude  " + it.longitude.toString()
                latitude.text = textLatitude
                longtitude.text = textLongitude
            }
        }
    }
}