// features/login/RegisterActivity.kt

package com.example.todoapp.features.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val usernameEditText = findViewById<EditText>(R.id.editTextUsernameRegister)
        val passwordEditText = findViewById<EditText>(R.id.editTextPasswordRegister)
        val registerButton = findViewById<Button>(R.id.buttonRegister)
        val loginButton = findViewById<Button>(R.id.buttonGoToLogin)

        registerButton.setOnClickListener {
            // ... Kayıt olma mantığı buraya gelecek ...
            Toast.makeText(this, "Kayıt işlemi başarılı!", Toast.LENGTH_SHORT).show()
            finish() // Kayıt olduktan sonra LoginActivity'ye dön
        }

        loginButton.setOnClickListener {
            finish() // Giriş ekranına dön
        }
    }
}