package com.example.Chatbotik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chatbotik.R
import com.example.chatbotik.databinding.ActivityMainBinding
import com.example.chatbotik.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button8.setOnClickListener {
            val intent = Intent(this, PsyChooses::class.java)

            val namePer1 = binding.editTextText.text
            val surnamePer1 = binding.editTextText2.text
            Log.d("MyLog","$namePer1 $surnamePer1")
            intent.putExtra("namePer1", "$namePer1")
            intent.putExtra("surnamePer1", "$surnamePer1")

            startActivity(intent)
            finish()
        }
    }
}