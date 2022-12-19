package com.example.pa_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pa_app.R
import com.example.pa_app.adapters.UtilizadorAdapter
import com.example.pa_app.models.Utilizador
import com.google.firebase.database.*

class VisualizarUtilizadores : AppCompatActivity() {

    private lateinit var userRecyclerView : RecyclerView
    private lateinit var tvLoadingData : TextView
    private lateinit var UserList : ArrayList<Utilizador>
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualizar_utilizadores)

        userRecyclerView = findViewById(R.id.userList)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.LoadingData)

        UserList = arrayListOf<Utilizador>()

        getUserData()

    }

    private fun getUserData() {
        userRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Utilizador")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                UserList.clear()
                if(snapshot.exists()){
                    for(userSnap in snapshot.children){
                        val userData = userSnap.getValue(Utilizador::class.java)
                        UserList.add(userData!!)
                    }
                    val mAdapter = UtilizadorAdapter(UserList)
                    userRecyclerView.adapter = mAdapter
                    mAdapter.setOnItemClicklistener(object : UtilizadorAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@VisualizarUtilizadores,Utilizador_Detalhes::class.java)

                            intent.putExtra("UserId",UserList[position].UserId)
                            intent.putExtra("NomeUser",UserList[position].NomeUser)
                            intent.putExtra("EmailUser",UserList[position].EmailUser)
                            intent.putExtra("PasswordUser",UserList[position].PasswordUser)
                            intent.putExtra("NifUser",UserList[position].NifUser)
                            startActivity(intent)
                        }

                    })
                    userRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}















