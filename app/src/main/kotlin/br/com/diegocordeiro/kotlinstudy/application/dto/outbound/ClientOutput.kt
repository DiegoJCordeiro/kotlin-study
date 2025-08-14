package br.com.diegocordeiro.kotlinstudy.application.dto.outbound

import kotlinx.serialization.Serializable

@Serializable
data class ClientCreateOutput(
    val id: String?
)

@Serializable
data class ClientUpdateOutput(
    val id: String
)

@Serializable
data class ClientDeleteOutput(
    val id: String,
    val wasDeleted: Boolean
)
