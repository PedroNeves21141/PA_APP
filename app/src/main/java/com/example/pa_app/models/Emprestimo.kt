package com.example.pa_app.models
import java.time.LocalDateTime

data class Emprestimo (
    var IdEmprestimo : String? = null,
    var data : LocalDateTime? = null,
    var dataDevolucao : LocalDateTime? = null,
    var quantidate : Int? = null
)