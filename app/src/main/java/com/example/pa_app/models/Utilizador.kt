package com.example.pa_app.models

import androidx.room.Relation

data class Utilizador (
    var UserId: String? = null,
    var NomeUser: String? = null,
    var EmailUser: String? = null,
    var PasswordUser: String? = null,
    var NifUser: String? = null,
)