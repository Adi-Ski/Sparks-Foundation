package com.example.aditya

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class After : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.after)
        auth = FirebaseAuth.getInstance()
        val logBtn: Button = findViewById(R.id.log_out)
        logBtn.setOnClickListener { signOut() }
        getUserProfile()
    }

    private fun signOut() {
        auth.signOut()
        startActivity(Intent(this, SignIn::class.java))
        finish()
    }

    private fun getUserProfile() {
        val user = auth.currentUser
        user?.let {

            val mail: TextView = findViewById(R.id.mail)
            val name: TextView = findViewById(R.id.name)
            val userId: TextView = findViewById(R.id.id)
            val profile: ImageView = findViewById(R.id.profile)

            mail.text = user.email
            name.text = user.displayName
            userId.text = user.uid
            Picasso.get().load(user.photoUrl).into(profile)
        }
    }
}