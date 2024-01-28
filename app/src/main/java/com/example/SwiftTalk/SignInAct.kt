package com.example.SwiftTalk

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.chatbotik.R
import com.example.chatbotik.databinding.ActivitySignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.Timer
import kotlin.concurrent.timerTask

class SignInAct : AppCompatActivity() {
    lateinit var launcher: ActivityResultLauncher<Intent>
    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivitySignInBinding
    private val autoTypeText = "Привествую вас в приложении для получения психологической помощи и консультаций. Изначально пройдите регистрацию через Google."
    private val delay: Long = 25
    private val timer = Timer()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        startAutoTypeText()
        auth = Firebase.auth
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)
                if(account != null){
                    firebaseAuthWithGoogle(account.idToken!!)
                }
            } catch (e: ApiException){
                Log.d("MyLog","Api exception")
            }
        }
        binding.bSignIn.setOnClickListener {
            signInWithGoogle()
        }
        checkAuthState()
        binding.button7.setOnClickListener {
            val i = Intent(this, Register::class.java)
            startActivity(i)
        }
    }

    private fun getClient(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(this, gso)
    }

    private fun signInWithGoogle(){
        val signInClient = getClient()
        launcher.launch(signInClient.signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String){
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){
                Log.d("MyLog","Google signIn done")
                checkAuthState()
            } else {
                Log.d("MyLog","Google signIn error")
            }
        }
    }

    private fun checkAuthState(){
        if(auth.currentUser != null){
            val i = Intent(this, PsyChooses::class.java)
            startActivity(i)
        }
    }
    private fun startAutoTypeText() {
        var index = 0
        timer.scheduleAtFixedRate(timerTask {
            runOnUiThread {
                if (index < autoTypeText.length) {
                    val currentText = autoTypeText.substring(0, index + 1)
                    binding.textViewAutoType.text = currentText
                    index++
                } else {
                    timer.cancel()
                }
            }
        }, delay, delay)
    }

}