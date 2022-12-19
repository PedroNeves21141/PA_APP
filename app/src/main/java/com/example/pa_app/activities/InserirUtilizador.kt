package com.example.pa_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.pa_app.R
import com.example.pa_app.models.Utilizador
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InserirUtilizador : AppCompatActivity() {

    private lateinit var Nome : EditText
    private lateinit var Email : EditText
    private lateinit var Password : EditText
    private lateinit var Nif : EditText
    private lateinit var btn : Button

    private lateinit var dbRef : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inserir_utilizador)

        Nome = findViewById(R.id.nome)
        Email = findViewById(R.id.email)
        Password = findViewById(R.id.password)
        Nif = findViewById(R.id.nif)
        btn = findViewById(R.id.button)

        dbRef = FirebaseDatabase.getInstance().getReference("Utilizador")

        btn.setOnClickListener {
            saveUserData()
        }
    }

    private fun saveUserData() {

        //getting values
        val NomeUser = Nome.text.toString()
        val EmailUser = Email.text.toString()
        val PasswordUser = Password.text.toString()
        val NifUser = Nif.text.toString()

        if (NomeUser.isEmpty()) {
            Nome.error = "Insire o seu Nome"
            return
        }
        if (EmailUser.isEmpty()) {
            Email.error = "Insire o seu Email"
            return
        }
        if (PasswordUser.isEmpty()) {
            Password.error = "Insire a sua Password"
            return
        }
        if (NifUser.isEmpty()) {
            Nif.error = "Insire o seu Nif"
            return
        }

        val UserId = dbRef.push().key!!

        val utilizador = Utilizador(UserId, NomeUser, EmailUser, PasswordUser, NifUser)

        dbRef.child(UserId).setValue(utilizador)
            .addOnCompleteListener{
                Toast.makeText(this, "Dados Inseridos com sucesso", Toast.LENGTH_LONG).show()

                Nome.text.clear()
                Email.text.clear()
                Password.text.clear()
                Nif.text.clear()

            }.addOnFailureListener{ error ->
                Toast.makeText(this, "Error ${error.message}", Toast.LENGTH_LONG).show()
            }

    }
}









