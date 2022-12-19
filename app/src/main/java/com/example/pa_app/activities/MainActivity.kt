package com.example.pa_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.pa_app.R

class MainActivity : AppCompatActivity() {

    private lateinit var button_add : Button
    private lateinit var button_ver : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_add = findViewById(R.id.button_add)
        button_ver = findViewById(R.id.button_ver)

        button_add.setOnClickListener {
            val intent = Intent(this, InserirUtilizador::class.java)
            startActivity(intent)
        }

        button_ver.setOnClickListener {
            val intent2 = Intent(this, VisualizarUtilizadores::class.java)
            startActivity(intent2)
        }


    }
}