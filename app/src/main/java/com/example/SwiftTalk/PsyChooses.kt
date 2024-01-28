package com.example.SwiftTalk


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.chatbotik.R

import com.example.chatbotik.databinding.ActivityPsyChoosesBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class PsyChooses : AppCompatActivity() {
    lateinit var binding: ActivityPsyChoosesBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPsyChoosesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        goNewView(binding)
        auth = Firebase.auth
    }
    private fun goNewView(binding: ActivityPsyChoosesBinding) {
        val namePer = intent.getStringExtra("namePer1")
        val surnamePer = intent.getStringExtra("surnamePer1")
        Log.d("MyLog","$namePer $surnamePer")
            binding.button2.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("name", binding.button2.text)
                intent.putExtra("namePer", "$namePer")
                intent.putExtra("surnamePer", "$surnamePer")
                startActivity(intent)
                finish()
        }
        binding.button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra("name", binding.button.text)
            intent.putExtra("namePer", namePer)
            intent.putExtra("surnamePer", surnamePer)
            startActivity(intent)
            finish()
        }
        binding.button3.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra("name", binding.button3.text)
            intent.putExtra("namePer", namePer)
            intent.putExtra("surnamePer", surnamePer)
            startActivity(intent)
            finish()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_mus, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.exit){
            auth.signOut()
            finish()
            val i = Intent(this, SignInAct::class.java)
            startActivity(i)
        }else if (item.itemId == R.id.music_bt){
            val i = Intent(this, Music::class.java)
            startActivity(i)
        }
        return super.onOptionsItemSelected(item)
    }
}