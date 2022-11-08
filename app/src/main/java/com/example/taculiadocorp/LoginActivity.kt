package com.example.taculiadocorp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.system.exitProcess

class LoginActivity : AppCompatActivity() {
    //BOTONES
    private lateinit var btnIniciarSesion: Button
    private lateinit var btnFacebook: Button

    //TEXT VIEW
    private lateinit var txtRegister: TextView
    private lateinit var txtForgetPass: TextView
    //---------------------

    //EDIT TEXT
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//Botones
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)
        txtRegister = findViewById(R.id.txtRegister)
        btnFacebook = findViewById(R.id.btnFacebook)
        txtForgetPass = findViewById(R.id.txtForgetPass)
//EditText
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)

        txtRegister.setOnClickListener {
            val intentRegister: Intent = Intent(this,RegisterActivity::class.java)
            startActivity(intentRegister)

        }
        btnIniciarSesion.setOnClickListener {
            val intentFraude: Intent = Intent(this,MainActivity::class.java)
            startActivity(intentFraude)
            finish()
        }

    //PRUEBA PARA EL TEXTO CLICKEABLE
     /*   txtForgetPass.setOnClickListener{
            val intentMain: Intent = Intent(this,MainActivity::class.java)
            startActivity(intentMain)
        }
        */
    }
}