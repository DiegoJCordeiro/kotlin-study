package br.com.diegocordeiro.kotlinstudy.application.dto.outbound

import kotlinx.serialization.Serializable

@Serializable
data class ProfessionalCreateOutput(
    val id: String?
)

@Serializable
data class ProfessionalUpdateOutput(
    val id: String
)

@Serializable
data class ProfessionalDeleteOutput(
    val id: String,
    val wasDeleted: Boolean
)
