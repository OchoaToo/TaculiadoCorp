package com.example.taculiadocorp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

class DetailsActivity : AppCompatActivity() {

    private lateinit var imgAlerta: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        imgAlerta = findViewById(R.id.imgAlerta)

        imgAlerta.setOnClickListener {

            Toast.makeText(this, "AUN NO PROGRAMADO", Toast.LENGTH_LONG).show()

        }


    }
}