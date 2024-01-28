package com.example.Chatbotik

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatbotik.R
import com.example.chatbotik.databinding.ActivityMainBinding
import com.example.chatbotik.databinding.UserListItemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var auth: FirebaseAuth
    lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val nameSpec = intent.getStringExtra("name")
        val namePer = intent.getStringExtra("namePer")
        val surnamePer = intent.getStringExtra("surnamePer")
        Log.d("MyLog","$namePer $surnamePer")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        setUpActionBar()
        val database = Firebase.database
        val selectedChatId = if (auth.currentUser?.displayName.isNullOrEmpty())"$namePer $surnamePer" else auth.currentUser?.displayName
        val myRef = database.getReference("messages/$nameSpec/$selectedChatId")
        binding.bSend.setOnClickListener {
            myRef.child(myRef.push().key ?: "blabla").setValue(User(selectedChatId, binding.edMessage.text.toString()))
            binding.edMessage.setText("")
        }
        binding.imageView.setOnClickListener {
            val i = Intent(this, PsyChooses::class.java)
            startActivity(i)
            finish()
        }
        onChangeListener(myRef)
        initRcView()

    }

    private fun initRcView() = with(binding){
        adapter = UserAdapter()
        rcView.layoutManager = LinearLayoutManager(this@MainActivity)
        rcView.adapter = adapter

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

    private fun onChangeListener(dRef: DatabaseReference){
        auth = Firebase.auth
        dRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<User>()
                for(s in snapshot.children){
                    val user = s.getValue(User::class.java)
                    if(user != null)list.add(user)
                }
                adapter.submitList(list)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun setUpActionBar(){
        val ab = supportActionBar
        Thread{
            runOnUiThread {
                val nameSpec = intent.getStringExtra("name")
                Log.d("MyLog", "$nameSpec")
                val remakename = when(intent.getStringExtra("name")){
                    "Общий психолог" -> "общим психологом"
                    "Психолог по семейным проблемам" -> "семейным психологом"
                    "Психолог по проблемам по учебе" -> "школьным психологом"
                    else -> "человеком"
                }
                ab?.title = "Чат с $remakename"
            }
        }.start()

    }
}
