package com.example.pa_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.pa_app.R
import com.example.pa_app.models.Utilizador
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class Utilizador_Detalhes : AppCompatActivity() {

    private lateinit var id_user : TextView
    private lateinit var nome_user : TextView
    private lateinit var email_user : TextView
    private lateinit var password_user : TextView
    private lateinit var nif_user : TextView
    private lateinit var btn_update : Button
    private lateinit var btn_delete : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utilizador_detalhes)

        initView()
        setValuesToViews()

        btn_update.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("UserId").toString(),
                intent.getStringExtra("NomeUser").toString(),
                intent.getStringExtra("EmailUser").toString(),
                intent.getStringExtra("PasswordUser").toString(),
                intent.getStringExtra("NifUser").toString(),
            )
        }
        btn_delete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("UserId").toString(),
            )
        }
    }

    private fun deleteRecord(
        id:String,
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Utilizador").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this,"Utilizador removido",Toast.LENGTH_LONG).show()

            val intent = Intent(this, VisualizarUtilizadores::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        id_user = findViewById(R.id.id_user)
        nome_user = findViewById(R.id.nome_user)
        email_user = findViewById(R.id.email_user)
        password_user = findViewById(R.id.password_user)
        nif_user = findViewById(R.id.nif_user)
        btn_update = findViewById(R.id.btn_update)
        btn_delete = findViewById(R.id.btn_delete)
    }

    private fun setValuesToViews() {
        id_user.text = intent.getStringExtra("UserId")
        nome_user.text = intent.getStringExtra("NomeUser")
        email_user.text = intent.getStringExtra("EmailUser")
        password_user.text = intent.getStringExtra("PasswordUser")
        nif_user.text = intent.getStringExtra("NifUser")
    }

    private fun openUpdateDialog(
        UserId: String,
        NomeUser: String,
        EmailUser: String,
        PasswordUser: String,
        NifUser: String,

    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_utilizador_update,null)

        mDialog.setView(mDialogView)

        val nome_update = mDialogView.findViewById<EditText>(R.id.nome_update)
        val email_update = mDialogView.findViewById<EditText>(R.id.email_update)
        val password_update = mDialogView.findViewById<EditText>(R.id.password_update)
        val nif_update = mDialogView.findViewById<EditText>(R.id.nif_update)

        val btn_update = mDialogView.findViewById<Button>(R.id.button_update)

        nome_update.setText(intent.getStringExtra("NomeUser").toString())
        email_update.setText(intent.getStringExtra("EmailUser").toString())
        password_update.setText(intent.getStringExtra("PasswordUser").toString())
        nif_update.setText(intent.getStringExtra("NifUser").toString())

        mDialog.setTitle("Atualizando $NomeUser ")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btn_update.setOnClickListener {
            UpdateUtilizador(
                UserId,
                nome_update.text.toString(),
                email_update.text.toString(),
                password_update.text.toString(),
                nif_update.text.toString()
            )
            Toast.makeText(applicationContext,"Dados do Utilizador atualizados",Toast.LENGTH_LONG).show()

            nome_user.text = nome_update.text.toString()
            email_user.text = email_update.text.toString()
            password_user.text = password_update.text.toString()
            nif_user.text = nif_update.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun UpdateUtilizador(
        id:String,
        nome:String,
        email:String,
        password:String,
        nif:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Utilizador").child(id)
        val userInfo = Utilizador(id,nome,email,password,nif)
        dbRef.setValue(userInfo)
    }
}