package com.example.todoapp.ui.register

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.todoapp.R
import com.example.todoapp.data.repository.AuthRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    @Inject
    lateinit var authRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val usernameEditText = findViewById<EditText>(R.id.editTextUsernameRegister)
        val passwordEditText = findViewById<EditText>(R.id.editTextPasswordRegister)
        val registerButton = findViewById<Button>(R.id.buttonRegister)
        val loginButton = findViewById<Button>(R.id.buttonGoToLogin)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            lifecycleScope.launch {
                if (username.isNotEmpty() && password.isNotEmpty()) {
                    val isRegistered = authRepository.register(username, password.hashCode().toString())
                    if (isRegistered) {
                        Toast.makeText(this@RegisterActivity, "Kayıt işlemi başarılı!", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@RegisterActivity, "Bu kullanıcı adı zaten mevcut.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@RegisterActivity, "Kullanıcı adı ve şifre boş bırakılamaz.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        loginButton.setOnClickListener {
            finish()
        }
    }
}