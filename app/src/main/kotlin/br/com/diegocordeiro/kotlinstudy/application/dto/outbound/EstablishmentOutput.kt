package br.com.diegocordeiro.kotlinstudy.application.dto.outbound

import kotlinx.serialization.Serializable

@Serializable
data class EstablishmentCreateOutput(
    val id: String?
)

@Serializable
data class EstablishmentUpdateOutput(
    val id: String
)

@Serializable
data class EstablishmentDeleteOutput(
    val id: String,
    val wasDeleted: Boolean
)