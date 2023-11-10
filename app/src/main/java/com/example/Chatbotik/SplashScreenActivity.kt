package com.example.Chatbotik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.chatbotik.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar!!.hide()
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, SignInAct::class.java)
            startActivity(intent)
            finish()
        }, 5000)

    }
}