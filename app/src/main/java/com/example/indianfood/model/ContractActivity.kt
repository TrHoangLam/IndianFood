package com.example.indianfood.model

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.example.indianfood.R
import com.example.indianfood.databinding.ActivityContractBinding

class ContractActivity : AppCompatActivity() {

    private val binding :ActivityContractBinding by lazy {
        ActivityContractBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.openWebPage.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=100010993113076"))
            startActivity(intent)
        }
        binding.openPhonecall.setOnClickListener{
            val phone = "0768655695"
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:" + phone)
            startActivity(intent)
        }
        binding.button3.setOnClickListener{
            val text = binding.text.text.toString()
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,text)
            startActivity(Intent.createChooser(intent,"Share Via"))
        }
    }
}