package com.example.pa_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pa_app.R
import com.example.pa_app.models.Utilizador

class UtilizadorAdapter(private val userList: ArrayList<Utilizador>) :
    RecyclerView.Adapter<UtilizadorAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClicklistener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent,false)
        return ViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val currentUser = userList[position]
        holder.NomeUser.text = currentUser.NomeUser
        holder.EmailUser.text = currentUser.EmailUser
        holder.NifUser.text = currentUser.NifUser
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val NomeUser : TextView = itemView.findViewById(R.id.nome_user)
        val EmailUser : TextView = itemView.findViewById(R.id.email_user)
        val NifUser : TextView = itemView.findViewById(R.id.nif_user)

        init{
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

}