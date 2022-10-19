package com.example.taculiadocorp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    //BOTONES
    private lateinit var btnIniciarSesion: Button
    private lateinit var btnFacebook: Button
    private lateinit var btnRegistro: Button
    //---------------------

    //EDIT TEXT
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//Botones
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)
        btnRegistro = findViewById(R.id.btnRegistro)
        btnFacebook = findViewById(R.id.btnFacebook)
//EditText
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)

        btnRegistro.setOnClickListener {

        }


    }
}