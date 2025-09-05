package com.example.todoapp.features.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.R
import com.example.todoapp.features.task.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameEditText = findViewById<EditText>(R.id.editTextUsernameLogin)
        val passwordEditText = findViewById<EditText>(R.id.editTextPasswordLogin)
        val loginButton = findViewById<Button>(R.id.buttonLogin)
        val registerButton = findViewById<Button>(R.id.buttonGoToRegister)

        loginButton.setOnClickListener {
            // ... Giriş mantığı buraya gelecek ...
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}