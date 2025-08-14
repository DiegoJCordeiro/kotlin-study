package br.com.diegocordeiro.kotlinstudy.application.dto.outbound

import kotlinx.serialization.Serializable

@Serializable
data class AddressCreateOutput(
    val id: String?
)

@Serializable
data class AddressUpdateOutput(
    val id: String
)

@Serializable
data class AddressDeleteOutput(
    val id: String,
    val wasDeleted: Boolean = false
)