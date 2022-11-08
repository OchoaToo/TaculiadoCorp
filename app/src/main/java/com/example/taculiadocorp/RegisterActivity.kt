package com.example.taculiadocorp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class RegisterActivity : AppCompatActivity() {
    //BUTTON

    //TxtView
    private lateinit var txtIniciarSesion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)



        txtIniciarSesion = findViewById(R.id.txtIniciarSesion)


        txtIniciarSesion.setOnClickListener{
            val intentLogin: Intent = Intent(this,LoginActivity::class.java)
            startActivity(intentLogin)
            finish()
        }


    }

}