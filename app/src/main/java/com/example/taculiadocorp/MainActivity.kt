package com.example.taculiadocorp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

//TODO: Crear Pantalla de Registro
class MainActivity : AppCompatActivity() {
    private lateinit var btn1: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById(R.id.btn1)

        /*MANDO EL LOGIN PRIMERO Para pruebas para cambiarlo ve al AndroidManifest y cambias el LoginActivyti por MainActivity
        Primero comprobar si ehay una sesion iniciada si no, mandar el activity login
        ~okto
         */
    }
}